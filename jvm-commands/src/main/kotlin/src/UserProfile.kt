package src

class UserProfile: AbstractWarrior(){

    var role : Roles? = null

    override var isKilled : Boolean = false
    override val name: String = "User"

    var isBattle = false
    var enemy : AbstractWarrior? = null
    var run = false


    //навыки
    override var chanceEvade: Int = 5 // ловкость
    override var accuracy: Int = 75 // точность


    //ОБЩИЕ ЗНАЧЕНИЯ
    override var maxHealth = 80       //здоровье
    private var totalEnergy = 115 //выносливость
    private var totalHunger = 100 //голод
    private var totalWater = 100 //жажда

    //Экипировка
    override var weapon = Weapons.createPistol()
    override var armor = Armors.createWindbreaker()


    //время
    private var hours : Int = 0
    private var minute : Int = 0
    var time : String = "[00:00]"

    fun time(){
        minute+= (15..32).random()
        while (minute > 60) {
            minute -= 60
            hours++
        }
        if (hours ==24) hours = 0

        time = String.format("[%02d:%02d]",hours,minute)
    }




    var inventory : MutableList<AbstractItems> = mutableListOf()


    //ИЗМЕНЯЕМЫЕ ЗНАЧЕНИЯ
    override var currentHealth = 80
    var currentHunger = 100
    var currentEnergy = 115
    var currentWater = 100
    var currentCapacity = 20


    fun incEnergy(count : Int){
        if ((count+currentEnergy)> totalEnergy) currentEnergy = totalEnergy
        else currentEnergy+=count
    }

    fun incWater(count : Int){
        if ((count+currentWater)> totalWater) currentWater = totalWater
        else currentWater+=count
    }

    fun subtractEnergy(count: Int){
        if (currentEnergy-count > 0) currentEnergy-=count
        else currentEnergy = 0
    }


    fun getEnergy() : String{
        var sqr = ""
        val oneSqrEnergy : Int = totalEnergy/9
        for(i in 0..totalEnergy step oneSqrEnergy){
            if (i < currentEnergy){
                sqr += "▰"
            }else sqr += "▱"
        }
        return "<:energy:954165796609876000> $sqr $currentEnergy/$totalEnergy"
    }


    fun getWater() : String{
        var sqr = ""
        val oneSqrEnergy : Int = totalWater/9
        for(i in 0..totalWater step oneSqrEnergy){
            sqr += if (i < currentWater) "▮" else "▯"
        }
        return "<:water:954334824741634108> $sqr $currentWater/$totalWater"
    }
    fun getHunger() : String{
        var sqr = ""
        val oneSqrEnergy : Int = totalHunger/9
        for(i in 0..totalHunger step oneSqrEnergy){
            sqr += if (i < currentHunger) "▮" else "▯"
        }
        return "<:eat:954390737154347028> $sqr $currentHunger/$totalHunger"
    }

    fun getInventory() : String{
        var listInventory = ""
        for (i in 0..inventory.lastIndex){
            listInventory += inventory[i].inventoryName
            listInventory += "\n"
        }
        return listInventory
    }

}