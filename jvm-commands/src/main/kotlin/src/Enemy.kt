package src

object Enemy {
    fun banditNewbie() : AbstractWarrior {
        return object : AbstractWarrior() {
            override val name = "БАНДИТ(новичок)"
            override var maxHealth: Int = 20
            override var accuracy: Int = 60
            override var weapon: AbstractWeapon = Weapons.createPistol()
            override var armor: AbstractArmor = Armors.createWindbreaker()
            override var mask: AbstractMask = Masks.createRespirator()
            override var currentHealth: Int = maxHealth
            override var isKilled: Boolean = false
            override val chanceEvade: Int = 5
            override var protection: Int = armor.protection + mask.protection
    }
}}

