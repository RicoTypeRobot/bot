package src

abstract class AbstractArmor : AbstractItems() {
    abstract val type : String
    abstract val protection : Int
}