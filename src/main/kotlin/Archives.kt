import java.util.*

class Archives (val name: String, var notes: MutableList<String> = mutableListOf()){

    //вывести запись на экран
    fun showPickedNote() : Boolean{
        print("Введите номер заметки: ")
        val usersChoise = Scanner(System.`in`)

        while(true){
            val numberOfNote = usersChoise.nextLine()
            when(HelpClass.helperLmao.isUserChoiseIsNumber(numberOfNote)){
                true -> {
                    if((numberOfNote.toInt() > 0) and (numberOfNote.toInt() <= notes.size)) {
                        print("--------------------------------\n")
                        print("${notes[(numberOfNote.toInt() - 1)]} \n" +
                                "--------------------------------\n0. Назад\n")
                        when(usersChoise.nextLine()){
                            "0" -> return true
                            else -> print("Введите 0 для для возврата на предыдущий экран: ")
                        }
                        return true
                    }
                    else{
                        print("Заметка с таким номером не найдена, повторите ввод: ")
                    }
                }
                false -> {print("Введите корректное значение: ")}
            }
        }

        return true
    }
}
//upd