package src

object Masks {
    fun createRespirator():AbstractMask {
        return object : AbstractMask(){
            override val image: String = "https://wampi.ru/image/RJ536Xx"
            override val id: String = "#3"
            override val rare: String = "Обычный"
            override val emoji: String = "<:mask_respirator:956927039917678643>"
            override val inventoryName: String = "$emoji Респиратор"
            override val price: Int = 200
            override val weight: Int = 1
            override val chanceDrop: Int = 1
            override val name: String = "Респиратор"
            override val type: String = "Легкая"
            override val protection: Int = 0
        }
    }
}