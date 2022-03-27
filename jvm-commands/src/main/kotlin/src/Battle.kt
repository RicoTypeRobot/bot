package src

import com.jessecorbett.diskord.api.common.User


class Battle(user : User) {



    private val u = user
    private val chanceEnemy = (0..25)
    private val chanceEscape = 25
    private val subEnergy = (3..15)
    private val subEnergyEscape = (30..43)



    init {
        //увеличение времени
        Users.listUsers[u]!!.time()
    }

    fun getEvent() : com.jessecorbett.diskord.api.channel.Embed {
        if (Users.listUsers[u]!!.currentEnergy == 0) return noEnergy(u)
        return when((0..100).random()){
            in chanceEnemy -> {battleEnemy(getEnemy(1))}
            else -> {exploration()}
        }
    }

    fun battleEnemy(enemy: AbstractWarrior): com.jessecorbett.diskord.api.channel.Embed {
        Users.listUsers[u]!!.subtractEnergy((subEnergy.random()))
        //Проверка находится ли данный пользователь в бою, если этот противник только появился значит присваиваем статус боя как активный
        //И добавляем в поле класса противников этого пользователя нового противника
        if (!Users.listUsers[u]!!.isBattle) {
            Users.listUsers[u]!!.isBattle = true
            Users.listUsers[u]!!.enemy = enemy
            return attack(u, enemy)
        }
        //Проведение атаки друг на друга, с учетем шанса получения крит.дамага , а так же шанс уклона и попадания
        if(Users.listUsers[u]!!.currentEnergy != 0)
            Users.listUsers[u]!!.attack(enemy)
        if (enemy.isKilled) {
            Users.listUsers[u]!!.enemy = null
            Users.listUsers[u]!!.isBattle = false
            return killEnemy(enemy,u)
        }
        Users.listUsers[u]!!.enemy!!.attack(Users.listUsers[u]!!)
        if (Users.listUsers[u]!!.isKilled){
            Users.listUsers.remove(u)
            return dead(enemy)
        }
        // сбор значений и передача управления
        return battle(u, enemy)

    }

    fun escape(enemy: AbstractWarrior) : com.jessecorbett.diskord.api.channel.Embed {
        Users.listUsers[u]!!.subtractEnergy((subEnergyEscape.random()))
        return if (Users.listUsers[u]!!.currentEnergy == 0) {
            Users.listUsers[u]!!.run = true
            battleEnemy(enemy)
        } else if (chanceEscape.realizedChance()) escapeDone(u)
        else {
            Users.listUsers[u]!!.run = true
            battleEnemy(enemy)
        }
    }

    fun exploration() : com.jessecorbett.diskord.api.channel.Embed{
        Users.listUsers[u]!!.subtractEnergy((subEnergy.random()))
        return com.jessecorbett.diskord.api.channel.Embed(
            title = Users.listUsers[u]!!.time,
            color = defaultColor,
            description = phrasesDefault((0..7).random()) + "\n\n" + Users.listUsers[u]!!.getEnergy()
        )
    }
}

fun getEnemy(int: Int) : AbstractWarrior {
    return when(int){
        1 -> Enemy.banditNewbie()
        else -> Enemy.banditNewbie()
    }
}