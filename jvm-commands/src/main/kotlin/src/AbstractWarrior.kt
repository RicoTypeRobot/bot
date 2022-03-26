package src

abstract class AbstractWarrior : InterfaceWarrior {
    abstract val name : String
    abstract var maxHealth: Int
    abstract var currentHealth: Int
    abstract var weapon: AbstractWeapon
    abstract var accuracy: Int

    //события
    var getCritDamage : Boolean = false
    var dodged : Boolean = false
    var recDamage : Int = 0

    fun toDefault(){
        getCritDamage = false
        dodged = false
        recDamage = 0
    }

    fun getDodgedEnemy(w : AbstractWarrior) : Boolean{
        w.dodged = w.chanceEvade.realizedChance()
        return !w.dodged
    }

    override fun attack(warrior: AbstractWarrior) {
        if (accuracy.realizedChance() && getDodgedEnemy(warrior)) {
            warrior.getDamage(weapon.currentDamage(warrior))
        }
    }

    fun getHP() : String{
        var sqr : String = ""
        val oneSqrEnergy : Int = maxHealth/10
        for(i in oneSqrEnergy..maxHealth step oneSqrEnergy){
            if (i <= currentHealth){
                sqr += "▰"
            }else sqr += "▱"
        }
        return "<:hp:954162722260520980> $sqr ${this.currentHealth}/${this.maxHealth}"
    }

    override fun getDamage(damage: Int) {
        if (currentHealth > damage) {
            recDamage = damage
            currentHealth -= damage
        }
        else {
            currentHealth = 0
            isKilled = true
        }
    }



}