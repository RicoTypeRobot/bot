package src

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedImage
import com.jessecorbett.diskord.api.common.User
import com.jessecorbett.diskord.util.Colors

fun dead(killer : AbstractWarrior): Embed {
    return Embed(
        title = "\uD83D\uDC80 СМЕРТЬ \uD83D\uDC80",
        color = battleColor,
        description = "Вы были убиты от рук ${killer.name}... Вам не удалось уследить за своими жизненно важными характеристиами, и поэтому вас настигла смерть",
        thumbnail = EmbedImage("https://st.depositphotos.com/1010652/3822/v/600/depositphotos_38222857-stock-illustration-8-bit-skull-icon.jpg")
    )
}

val commands = Embed(
    title = "Список команд",
    color = Colors.ORANGE,
    description = """
           **Добавляйте префикс `.` ко всем командам**
                            
           **🏅Статистика🏅**
           `профиль`,`? [команда/название предмета]`,`инвентарь`
                      
           **:dagger:Приключения:dagger:**
           `исследовать`,`атака`,`сбежать`
                        """.trimIndent()

)
val noItemException = Embed(
    title = "Ошибка",
    color = Colors.RED,
    description = "В вашем инвентаре отсутствует данный предмет"
)
fun getTester(u : User) : Embed = Embed(
    title = "Добро пожаловать, ${u.username}", description = """
        ```
        Консоль разработчика
        
        Alpha Version - 0.0.0.39
        
        Изменения в боте на 25.03.2022
        
        НОВОВВЕДЕНИЯ
        -Добавлена смерть персонажа и соответсвующее сообщение к ней.
        -В команду .профиль добавлен раздел экиппировка
        -Теперь в инвентаре не отображаются элементы экиппированные на пользователя
        -Навыки временно были удалены
        -Добавлена НОВАЯ КОМАНДА (.сбежать) 
        
        БАГИ
        -Исправлен баг, при котором противник не удалялся из списка противников пользователя
        -Теперь если пойти на исследование, значение Энергии будет равно 0, то вы увидите соответствующее сообщение
        -Исправлен баг при котором можно было воспользоваться командой сбежать, без соответствующего количество очков энергии
        -Теперь,если при исследовании был недостаток энергии, время отображается корректно если 
        
        ИЗМЕНЕНИЯ БАЛАНСА
        ```
                    """.trimIndent()
)
val noUserException = Embed(
    color = Colors.RED,
    title = "Ошибка",
    description = "Вас нет в списке пользователей, пожалуйста введите команду `.старт` и выберите свою профессию для начала игры"
)

val userHasRoleException = Embed(
    color = Colors.RED,
    title = "Ошибка",
    description = "Вы уже имеете роль!"
)

val startString = Embed(
    title = "Выберите кем вы были в прошлом :",
    fields = mutableListOf(
        EmbedField("Скоро", "__файл не найден__", true)
    ),
    description = "Введите команду `.выбрать [роль]` , чтобы выбрать того, кем вы были в прошлом. " +
            "В дальнейшем, данную роль можно сменить, но уже за отдельную плату!"
)

val investigateInWar = Embed(
    color = Colors.RED,
    title = "Ошибка!",
    description = "Вы не можете иссследовать, будучи в бою"
)

val noEnemys = Embed(
    color = 16711680,
    description = "На данный момент противники отсутствуют"

)



fun profileUser(author: User) : Embed = Embed(
    title = "\uD83D\uDC64 Профиль игрока: ${author.username}",
    thumbnail = EmbedImage(
        url = "https://files.vzlomannye-igry-dlya-android.net/files/8028/gp_icon.png"
    ),
    fields = mutableListOf(
        EmbedField("Роль: Тестер","""
            
                        ${Users.listUsers[author]!!.getHP()}
                        ${Users.listUsers[author]!!.getEnergy()}
                        
                        ${Users.listUsers[author]!!.getWater()}
                        ${Users.listUsers[author]!!.getHunger()}
                        
                        
        """.trimIndent(),true),
        EmbedField("Экипировка","""
            Маска - Отсутствует
            Костюм - Отсутствует
            Рюкзак - Отсутствует
            ${Users.listUsers[author]!!.weapon.emoji} ${Users.listUsers[author]!!.weapon.name}
        """.trimIndent(),true)
    )
)



val infoMakarych9mm = Embed(
    title = Weapons.createPistol().name,
    description = """
                        ${Weapons.createPistol().rare}
                        
                        Урон [${Weapons.createPistol().minDamage}-${Weapons.createPistol().maxDamage}]
                        Шанс КРИТ.УРОНА : ${Weapons.createPistol().chanceCriticalDamage}%
                        МНОЖ.урона при КРИТ : X${Weapons.createPistol().coefficientCriticalDamage}
                        
                        
                        Вес : ${Weapons.createPistol().weight}
                        Ср.цена : ${Weapons.createPistol().price} 
                        
                        
                        ```id : ${Weapons.createPistol().id}```
                        
                    """.trimIndent(),
    thumbnail = EmbedImage(Weapons.createPistol().image)
)






