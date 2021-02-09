package duke;

public class ListTaskCommand extends Command {

    public ListTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct list command.
     * If it is, it prints the task list.
     * Otherwise, it prints the error faced.
     *
     * @return String of list task command.
     */
    private String listTask() {
        try {
            if (parser.canParseListCommand(input)) {
                return ui.getTaskList(taskList);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the list task command.
     *
     * @return String of list task command.
     */
    @Override
    public String execute() {
        return listTask();
    }
}
