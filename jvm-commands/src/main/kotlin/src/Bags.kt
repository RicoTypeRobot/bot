package src

object Bags {
    fun createSchoolBag() : AbstractBag {
        return object : AbstractBag() {
            override val slots: Int = 4
            override val image: String = "https://wampi.ru/image/RJ5kgAa"
            override val id: String = "#5"
            override val rare: String= "Обычный"
            override val emoji: String = "<:school_bag:956925837536546826>"
            override val inventoryName: String = "$emoji Школьный рюкзак"
            override val price: Int = 500
            override val weight: Int = 1
            override val chanceDrop: Int = 1
            override val name: String = "Школьный рюкзак"

        }
    }
}