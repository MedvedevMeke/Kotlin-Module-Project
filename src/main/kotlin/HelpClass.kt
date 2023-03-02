import java.util.Scanner
class HelpClass {

    companion object helperLmao{

        //апдейт списка архивов
        fun updateArchivesList(archivesList: MutableList<Archives>) {
            if (archivesList.size == 0) {
                print("Архивы не найдены")
            } else {
                print("Список архивов: ")
                for (i in 1..archivesList.size) {
                    print("\n$i. ${archivesList[i - 1].name}")
                }
                print("\n--------------------------------\n")
            }
        }

        //создание архива
        val usersChoise = Scanner(System.`in`)
        fun createArchive(archivesList: MutableList<Archives>){
            println("Введите название архива")
            val newArchiveName = usersChoise.nextLine()
            archivesList.add(Archives("$newArchiveName"))
            println("Архив создан")
            updateArchivesList(archivesList)
            println(MenuEnum.MAINMENU.menuScreen)
        }
        //вывод выбранного архива и содержащихся в нем заметок
        fun chooseArchive(archivesList: MutableList<Archives>) : Boolean{
            if (archivesList.size == 0) {
                println("Архивы не найдены... \nСоздаем новый...")
                createArchive(archivesList)
            }
            else{
                print("Введите номер архива: ")

                while(true){
                    val archiveNumber = usersChoise.nextLine()
                    when(isUserChoiseIsNumber(archiveNumber)){
                        true -> {
                            if((archiveNumber.toInt() > 0) and (archiveNumber.toInt() <= archivesList.size)){
                                print("Текущий архив: ${archivesList[archiveNumber.toInt()-1].name}" +
                                        "\n-------------------------------- \n")
                                var checkVal : Boolean = isArchiveEmpty(archiveNumber.toInt()-1, archivesList)
                                if(checkVal == true){

                                    while(true){
                                        when(usersChoise.nextLine()){
                                            "1" -> {addNote(archivesList[archiveNumber.toInt()-1].notes); isArchiveEmpty(archiveNumber.toInt()-1, archivesList); checkVal = false}
                                            "2" -> {
                                                if(checkVal == true){
                                                    print("Введите корректный номер операции: ")
                                                }
                                                else{
                                                    archivesList[archiveNumber.toInt()-1].showPickedNote();
                                                    isArchiveEmpty((archiveNumber.toInt()-1), archivesList)
                                                }
                                            }
                                            "0" -> {updateArchivesList(archivesList); print("${MenuEnum.MAINMENU.menuScreen}\n"); return true}
                                            else -> print("Введите корректный номер операции: ")
                                        }
                                    }
                                }
                                else{
                                    while(true){
                                        when(usersChoise.nextLine()){
                                            "1" -> {addNote(archivesList[archiveNumber.toInt()-1].notes); isArchiveEmpty(archiveNumber.toInt()-1,  archivesList)}
                                            "2" -> {archivesList[archiveNumber.toInt()-1].showPickedNote(); isArchiveEmpty((archiveNumber.toInt()-1), archivesList) }
                                            "0" -> {updateArchivesList(archivesList); print("${MenuEnum.MAINMENU.menuScreen}\n");return true}
                                            else -> print("Введите корректный номер операции: ")
                                        }
                                    }
                                }
                            }
                            else{
                                print("Архив с данным номером не найден, повторите ввод: ")
                            }
                        }
                        false ->{
                            print("Введите корректный номер архива: ")
                        }
                    }
                }
            }
            return true
        }

        //добавление заметки
        fun addNote(notes: MutableList<String>){
            print("Введите заметку: \n")
            val newNote = usersChoise.nextLine()
            notes.add(newNote)
            print("Заметка добавлена\n")
        }

        //проверка архива на наличие заметок
        fun isArchiveEmpty(archiveNumber : Int, archivesList: MutableList<Archives>) : Boolean{
            if(archivesList[archiveNumber].notes.size == 0){
                print("В выбранном архиве нет заметок \n" +
                        "--------------------------------\n")
                println(MenuEnum.EMPTYARCHIVESELECT.menuScreen)
                return true
            }
            else{
                print("Текущий архив: ${archivesList[archiveNumber].name}\n" +
                        "--------------------------------\n")
                print("Список заметок:")
                for (i in 1..archivesList[archiveNumber.toInt()].notes.size){
                    if(archivesList[archiveNumber.toInt()].notes[i-1].toCharArray().size > 10){
                        print("\n$i. ${archivesList[archiveNumber.toInt()].notes[i-1].reversed().takeLast(10).reversed()}...")
                    }
                    else{
                        print("\n$i. ${archivesList[archiveNumber.toInt()].notes[i-1]}")
                    }
                }
                print("\n-------------------------------- \n")
                println(MenuEnum.ARCHIVESELECT.menuScreen)
                return false
            }
        }

        //проверка ввода
        fun isUserChoiseIsNumber( checkString : String) : Boolean{
            try{
                checkString.toInt()
                return true
            }
            catch (ex: NumberFormatException){
                return false
            }
        }
    }
}
//upd