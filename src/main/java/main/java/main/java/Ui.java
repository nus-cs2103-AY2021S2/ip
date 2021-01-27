package main.java;
import java.time.LocalDate;

/**
 * Class that deals with interactions with the user.
 */
class Ui {
    
    protected void processCommand(String[] input, TaskList taskList) {
        try {
            if (input[0].equals("list")) {
                taskList.listTask();
            } else if (input[0].equals("done")) {
                taskList.doneTask(Integer.valueOf(input[1]));
            } else if (input[0].equals("delete")) {
                taskList.delete(Integer.valueOf(input[1]));
            } else if (input[0].equals("todo")) {
                taskList.addToDo(input[1]);
            } else if (input[0].equals("deadline")) {
                taskList.addDeadline(input[1], LocalDate.parse(input[2]));
            } else if (input[0].equals("event")) {
                taskList.addEvent(input[1], LocalDate.parse(input[2]));
            } else if (input[0].equals("find")) {
                taskList.find(input[1]);
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
