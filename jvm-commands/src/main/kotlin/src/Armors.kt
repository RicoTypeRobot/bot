package src

object Armors {
    fun createWindbreaker(): AbstractArmor {
        return object : AbstractArmor() {
            override val type: String = "Легкая"
            override val protection: Int = 0
            override val image: String = "https://wampi.ru/image/Rj55bXq"
            override val id: String = "#2"
            override val rare: String = "Обычный"
            override val emoji: String = "<:windbreaker:956926494075150407>"
            override val inventoryName: String = "$emoji Ветровка"
            override val price: Int = 100
            override val weight: Int = 1
            override val chanceDrop: Int = 1
            override val name: String = "Ветровка"
        }
    }
}



