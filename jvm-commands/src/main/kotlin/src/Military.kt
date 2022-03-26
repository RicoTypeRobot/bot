package src

import com.jessecorbett.diskord.api.common.User

object Military {
    var name = "Военный"
    var description = """
         Владение легким оружием +5
         Владение средним оружием +5
         Владение тяжелым оружием +5
    """.trimIndent()

    var listMembers : MutableList<User> = mutableListOf()
}