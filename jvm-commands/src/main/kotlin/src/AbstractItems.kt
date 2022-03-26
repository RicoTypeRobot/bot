package src

import com.jessecorbett.diskord.api.common.User

abstract class AbstractItems {
    abstract val image : String
    abstract val id : String
    abstract val rare : String
    abstract val inventoryName : String
    abstract val price : Int
    abstract val weight : Int
    abstract val chanceDrop : Int
    abstract val name : String
    abstract val emoji : String

    open fun useItem(user : User){}

}