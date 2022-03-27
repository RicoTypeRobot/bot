package src


import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.api.common.User
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.util.Colors
import com.jessecorbett.diskord.util.discordCdn
import com.jessecorbett.diskord.util.sendMessage
import com.jessecorbett.diskord.util.sendReply



val defaultColor = Colors.GREEN
val battleColor = Colors.DARKRED

fun Int.realizedChance(): Boolean {
    return this >= (0..100).random()
}


suspend fun main() {
    bot("") {

        classicCommands(".") {

            command("команды") { message -> channel(message.channelId).sendReply(message, embed = commands) }
            command("старт") { message ->
                if (!Users.listUsers.containsKey(message.author)) {
                    channel(message.channelId).sendMessage(embed = startString)
                } else {
                    channel(message.channelId).sendMessage(embed = userHasRoleException)
                }
            }

            command("исследовать") { message ->
                if (Users.listUsers.containsKey(message.author))
                    if (Users.listUsers[message.author]!!.enemy == null)
                        channel(message.channelId).sendReply(message, embed = Battle(message.author).getEvent())
                    else channel(message.channelId).sendReply(message, embed = investigateInWar)
                else channel(message.channelId).sendMessage(embed = noUserException)
            }

            command("профиль") { message ->
                if (Users.listUsers.containsKey(message.author))
                    channel(message.channelId).sendMessage(embed = profileUser(message.author))
                else channel(message.channelId).sendMessage(embed = noUserException)
            }

            command("атака") { message ->
                if (Users.listUsers.containsKey(message.author))
                    if (Users.listUsers[message.author]!!.enemy != null)
                        channel(message.channelId).sendReply(message,
                            embed = Battle(message.author).battleEnemy(Users.listUsers[message.author]!!.enemy!!)
                        )
                    else channel(message.channelId).sendReply(message, embed = noEnemys)
                else channel(message.channelId).sendMessage(embed = noUserException)
            }

            command("сбежать") {message ->
                if (Users.listUsers.containsKey(message.author))
                    if (Users.listUsers[message.author]!!.enemy != null)
                        channel(message.channelId).sendReply(message,
                            embed = Battle(message.author).escape(Users.listUsers[message.author]!!.enemy!!)
                        )
                    else channel(message.channelId).sendReply(message, embed = noEnemys)
                else channel(message.channelId).sendReply(message, embed = noUserException)
            }

            command("инвентарь") { message ->
                if (Users.listUsers.containsKey(message.author))
                    channel(message.channelId).sendReply(
                        message, embed = Embed(
                            title = "Инвентарь",
                            description = Users.listUsers[message.author]!!.getInventory()
                        )
                    )
                else channel(message.channelId).sendReply(message, embed = noUserException)
            }
        }

        classicCommands(".выбрать ") {

            command(key = "Тест") { message ->
                if (!Users.listUsers.containsKey(message.author)) {
                    channel(message.channelId).sendReply(message, embed = getTester(message.author))
                    Users.listUsers[message.author] = UserProfile()
                    Users.listUsers[message.author]!!.role = Roles.Tester

                } else channel(message.channelId).sendReply(message, embed = userHasRoleException)
            }
        }

        classicCommands(".? ") {
            command(Weapons.createPistol().id) { message ->
                channel(message.channelId).sendReply(message, embed = infoMakarych9mm)
            }
        }
    }
}


