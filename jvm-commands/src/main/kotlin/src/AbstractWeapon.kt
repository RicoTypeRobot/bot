package src

import com.jessecorbett.diskord.api.common.User

abstract class AbstractWeapon : AbstractItems() {
    abstract val minDamage : Int
    abstract val maxDamage : Int
    abstract val coefficientCriticalDamage : Int
    abstract val chanceCriticalDamage : Int

    fun critDamageRelized(w : AbstractWarrior) : Boolean {
        return if (chanceCriticalDamage.realizedChance()){
            w.getCritDamage = true
            true
        } else false
    }

    fun currentDamage(w : AbstractWarrior): Int {
        return if (critDamageRelized(w)) (minDamage..maxDamage).random() * coefficientCriticalDamage else (minDamage..maxDamage).random()
    }
}
