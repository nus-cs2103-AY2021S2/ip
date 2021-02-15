package duke;
import java.time.LocalDate;

/**
 * Class that deals with interactions with the user.
 */
class Ui {

    /**
     * This method will process the command and then call the suitable method.
     *
     * @param parsedInput The parsed input string.
     * @param taskList The taskList which stores the list of tasks.
     */
    protected String processParsedCommand(String[] parsedInput, TaskList taskList) {
        String inputCommand = parsedInput[0];
        if (parsedInput[0].equals("list")) {
            return taskList.listTask();
        } else if (parsedInput[0].equals("done")) {
            return taskList.doneTask(Integer.valueOf(parsedInput[1]));
        } else if (parsedInput[0].equals("delete")) {
            return taskList.delete(Integer.valueOf(parsedInput[1]));
        } else if (parsedInput[0].equals("todo")) {
            return taskList.addToDo(parsedInput[1]);
        } else if (parsedInput[0].equals("deadline")) {
            return taskList.addDeadline(parsedInput[1], LocalDate.parse(parsedInput[2]));
        } else if (parsedInput[0].equals("event")) {
            return taskList.addEvent(parsedInput[1], LocalDate.parse(parsedInput[2]));
        } else if (parsedInput[0].equals("find")) {
            return taskList.find(parsedInput[1]);
        } else if (parsedInput[0].equals("sort")) {
            return taskList.sort(parsedInput[1]);
        }
        return "Invalid command.";
    }
}
