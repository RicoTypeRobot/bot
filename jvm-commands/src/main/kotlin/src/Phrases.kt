package src

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.common.User
import com.jessecorbett.diskord.util.Colors


fun phrasesDefault(int: Int): String {
    return when (int) {
        1 -> "-Увидел надпись на сгоревшем здании \" ДОБРО ПОЖАЛОВАТЬ В АД ГРЕШНИКИ! \"... Какого черта тут происходит?!"
        2 -> "-Нашел чей-то тайник в туалете. Оказалось это не тайник... Тьфу..."
        3 -> "-Никого и ничего не видно. Как тут можно выжить?"
        4 -> "-Увидел как двое бандитов со смехом растреляли какого-то бедолагу. Жаль, я не смог ему ничем помочь."
        5 -> "-Время прошло спокойно."
        6 -> "-Нашел старый Москвич у дороги. Обыскал салон и багажник. Ничего не нашел..."
        7 -> "-Видел возле старого подъезда группу бандитов пинающих какого-то зомби. Пожелал им спортивного успеха в футболе и пошел дальше..."
        else -> "-Время прошло спокойно."
    }
}

fun receivingItems(int: Int): String {
    return when (int) {
        1 -> "-Нашел заброшенный лагерь, в нем оказалось только это.\n"
        2 -> "-Бродя по заброшенному городу, я увидел свежий труп, в руках у него было это.\n"
        3 -> "-Нашел старый Москвич у дороги. Обыскал салон и багажник. И нашел это.\n"
        4 -> "-Проходя мимо статуи Ленина, я увидел блестящую крышку. Ого, это тайник! Вот что в нем было.\n"
        else -> "-Я нашел чей-то тайник!\n"
    }

}

fun escapeDone(author : User) : Embed {
    Users.listUsers[author]!!.enemy = null
    Users.listUsers[author]!!.isBattle = false
    Users.listUsers[author]!!.toDefault()
    return Embed(
        title = Users.listUsers[author]!!.time,
        color = Colors.GREEN,
        description = """
            |- Мне удалось оторваться от него! По крайней мере, будем надеятся, что это так.
            |
            |**Осталось энергии**
            |${Users.listUsers[author]!!.getEnergy()}
        """.trimMargin()
    )
}
fun escapeFail() : String = "- Попытка сбежать от него оказалась провальной."

fun noEnergy(u : User) : Embed = Embed(
    title = Users.listUsers[u]!!.time, description = "- Я очень устал, мне нужно отдохнуть"
)

fun attack(author: User, enemy: AbstractWarrior): Embed = Embed(
    title = Users.listUsers[author]!!.time,
    description = """
    |-На меня напал ${enemy.name} 
    |
    |У него ${enemy.currentHealth} здоровья и [${enemy.weapon.minDamage}-${enemy.weapon.maxDamage}] урона.
                                    """.trimMargin(),
    fields = mutableListOf(
        EmbedField(
            name = "Осталось энергии",
            value = Users.listUsers[author]!!.getEnergy(),
            inline = true
        )
    )
)

fun killEnemy(enemy: AbstractWarrior, author: User): Embed = Embed(
    title = Users.listUsers[author]!!.time,
    description = """
            - C ${enemy.name} было покончено
                                    
            Получено 48 опыта
        """.trimIndent()
)

fun battle(author: User, enemy: AbstractWarrior): Embed {
    var static = ""
    if (Users.listUsers[author]!!.run) {
        static += if (Users.listUsers[author]!!.currentEnergy == 0) "- У меня не хватило сил, чтобы начать побег"
        else escapeFail()
        Users.listUsers[author]!!.run = false
    }else{
        static += "- Я атакую ${enemy.name}."
        static += if (Users.listUsers[author]!!.currentEnergy == 0) "У меня не хватило сил, чтобы совершить атаку, поэтому я пропускаю ход"
        else if (enemy.dodged) " Мои выстрелы летят в молоко."
        else if (enemy.getCritDamage) " ОГО! Мой выстрел попап прямо в грудь противника, при этом, я нанес ему КРИТИЧЕСКИЙ урон множителем X${Users.listUsers[author]!!.weapon!!.coefficientCriticalDamage} и нанес ${Users.listUsers[author]!!.enemy!!.recDamage} урона!"
        else " Нанесено ${enemy.recDamage} урона."
    }
    static += "\n"
    static += "- ${enemy.name} атакует меня,"
    static += if (Users.listUsers[author]!!.dodged) " к счастью мне удалось увернуться"
    else if (Users.listUsers[author]!!.getCritDamage) "O НЕТ! Противник нанес мне КРИТИЧЕСКИЙ урон множителем Х${enemy.weapon.coefficientCriticalDamage}, при этом он нанес мне ${Users.listUsers[author]!!.recDamage} урона"
    else " и я теряю ${Users.listUsers[author]!!.recDamage} здоровья."
    if (Users.listUsers[author]!!.armor!!.protection > 0)
        static += " Моя броня взяла на себя часть урона в размере: ${Users.listUsers[author]!!.armor!!.protection} единиц"

    static += "\n\n"
    static += "**${enemy.name}** ${enemy.getHP()}"
    static += "\n\n"
    static += "**Ваши показатели**"
    static += "\n"
    static += Users.listUsers[author]!!.getHP()
    static += "\n"
    static += Users.listUsers[author]!!.getEnergy()

    Users.listUsers[author]!!.toDefault()
    enemy.toDefault()

    return Embed(
        title = Users.listUsers[author]!!.time,
        color = battleColor,
        description = static
    )
}
