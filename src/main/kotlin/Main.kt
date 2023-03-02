import java.util.Scanner
fun main() {
    println("Привет")

    val archivesList : MutableList<Archives> = mutableListOf()

    print(MenuEnum.MAINMENU.menuScreen)
    val usersChoise = Scanner(System.`in`)

    while(true){
        when(usersChoise.nextLine()){
            "1" -> HelpClass.helperLmao.chooseArchive(archivesList)
            "2" -> HelpClass.helperLmao.createArchive(archivesList)
            "0" -> {print("Выход из программы..."); break}
            else -> print("Введите корректный номер операции: ")
        }
    }
}
//upd