package src

interface InterfaceWarrior {

    var isKilled: Boolean
    val chanceEvade: Int

    fun attack(warrior: AbstractWarrior)
    fun getDamage(damage: Int)


}