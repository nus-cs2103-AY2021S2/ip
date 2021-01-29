package duke;

/**
 * This class translate the user input into commands understandable by Duke.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Construct a Parser from the specified list of task.
     */
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse the user input.
     * @param command input entered by user
     */
    void parse(String command) {
        if (command.equals("bye")) {
            Ui.exit();
        } else if (command.equals("list")) {
            taskList.listTask();
        } else if (command.startsWith("done")) {
            taskList.markDone(command.substring(5));
        } else if (command.startsWith("delete")) {
            taskList.deleteTask(command.substring(7));
        } else if (command.startsWith("find")) {
            taskList.findTask(command.substring(5));
        } else {
            taskList.addTask(command);
        }
    }
}
