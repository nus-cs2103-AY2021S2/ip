package duke;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct Delete command.
     * If it is, it removes the task and prints the Delete message.
     * Otherwise, it prints the exception faced.
     *
     * @return Message of remove task command.
     */
    private String removeTask() {
        try {
            if (parser.canParseIndexCommand(input, taskList.getSize())) {
                int index = parser.parseIndex(input, taskList.getSize());
                assert index < taskList.getSize() && index >= 0: "Index out of bounds";
                Task deleted = taskList.deleteTask(index);
                return ui.getDeleteTask(deleted);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the command to remove task.
     *
     * @return Message of command.
     */
    @Override
    public String execute() {
        return removeTask();
    }
}
