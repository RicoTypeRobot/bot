package src

import com.jessecorbett.diskord.api.common.Embed

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
    override var weapon : AbstractWeapon = Weapons.createPistol()
    override var armor : AbstractArmor = Armors.createWindbreaker()
    override var mask: AbstractMask = Masks.createRespirator()
    var bag: AbstractBag = Bags.createSchoolBag()
    override var protection: Int = armor.protection + mask.protection

    var currentSlots = bag.slots

    var inventory : MutableMap<String,AbstractItems> = mutableMapOf(
        weapon.name to weapon,
        armor.name to armor,
        mask.name to mask,
        bag.name to bag
    )

    fun getItemsInventory() : String{
        var str = ""
        inventory.forEach{
            str += "${it.value.emoji} ${it.key}"
            str += "\n"
        }
        return str
    }


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


}