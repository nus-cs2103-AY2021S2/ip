package duke;

public class ListTaskCommand extends Command {

    public ListTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct list command.
     * If it is, it prints the task list.
     * Otherwise, it prints the error faced.
     */
    private String listTask() {
        try {
            if (parser.canParseListCommand(input)) {
                return ui.printTaskList(taskList);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }

    /**
     * Executes the list task command.
     */
    @Override
    public String execute() {
        return listTask();
    }
}
