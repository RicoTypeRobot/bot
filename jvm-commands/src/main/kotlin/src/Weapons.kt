package src

object Weapons {
    fun createPistol() : AbstractWeapon {
        return object : AbstractWeapon() {
            override val image: String = "https://i.yapx.ru/RQ0ei.png"
            override val id: String = "#1"
            override val minDamage: Int = 3
            override val maxDamage: Int = 6
            override val rare: String = "Обычный"
            override val emoji: String = "<:Makarych9mm:955564038610354176>"
            override val inventoryName: String = "$emoji Макарыч(9мм)"
            override val price: Int = 300
            override val weight: Int = 1
            override val chanceCriticalDamage: Int = 25
            override val coefficientCriticalDamage: Int = 2
            override val chanceDrop: Int = 150
            override val name: String = "Макарыч (9мм)"
        }

    }
}