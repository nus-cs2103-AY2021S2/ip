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
     * @exception DukeException If no arguments are provided.
     */
    @Override
    public String execute() throws DukeException {
        if (parts.length == 1) { //no parameter provided
            throw new DukeException("Insufficient Arguments Provided");
        }

        if (parts.length > 2) { //too many parameters provided
            throw new DukeException("Too many arguments provided");
        }

        if (!Parser.isNumber(parts[1])) { //if parameter is not a number
            throw new DukeException("Parameter must be an number");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        assert tasks != null : "tasks cannot be null";

        if (index > tasks.getSize() || index < 0) {
            throw new DukeException("Task does not exist");
        }
        Task toMark = TaskList.getTasklist().get(index - 1);
        toMark.markAsDone();
        TaskList.getTasklist().get(index - 1).markAsDone();
        return Ui.showDoneText() + toMark.toString();
    }
}
