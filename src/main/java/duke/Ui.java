package duke;
import java.time.LocalDate;

/**
 * Class that deals with interactions with the user.
 */
class Ui {

    /**
     * This method will process the command and call other methods
     *
     * @param input the processed data input
     * @param taskList the taskList which stores the task
     */
    protected String processCommand(String[] input, TaskList taskList) {
        try {
            if (input[0].equals("list")) {
                return taskList.listTask();
            } else if (input[0].equals("done")) {
                return taskList.doneTask(Integer.valueOf(input[1]));
            } else if (input[0].equals("delete")) {
                return taskList.delete(Integer.valueOf(input[1]));
            } else if (input[0].equals("todo")) {
                return taskList.addToDo(input[1]);
            } else if (input[0].equals("deadline")) {
                return taskList.addDeadline(input[1], LocalDate.parse(input[2]));
            } else if (input[0].equals("event")) {
                return taskList.addEvent(input[1], LocalDate.parse(input[2]));
            } else if (input[0].equals("find")) {
                return taskList.find(input[1]);
            } else if (input[0].equals("sort")) {
                return taskList.sort(input[1]);
            }
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
        return null;
    }
}
