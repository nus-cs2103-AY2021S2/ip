/**
 * Handles the command where the user indicates completion of a task.
 */
public class DoneCommand extends Command {

    DoneCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    /**
     * Returns a string representation of the Task after it is marked a task as done.
     *
     * @return A string representation of the Task that is marked as done.
     * @exception InsufficientArgumentsException If no arguments are provided.
     */
    @Override
    public String execute() throws InsufficientArgumentsException {
        if (parts.length == 1) {
            throw new InsufficientArgumentsException("Insufficient arguments provided");
        }
        int index = Integer.parseInt(parts[1]);
        assert index >= 1 : "Value must be at least 1";
        Task toMark = TaskList.getTasklist().get(index - 1);
        toMark.markAsDone();
        TaskList.getTasklist().get(index - 1).markAsDone();
        return Ui.showDoneText() + toMark.toString();
    }
}
