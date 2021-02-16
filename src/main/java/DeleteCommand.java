/**
 * Handles the delete command, where the user removes an item from the list.
 */
public class DeleteCommand extends Command {

    DeleteCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    /**
     * Returns a string representation of the Task after it is deleted from the list.
     *
     * @return A string representation of the Task deleted.
     * @throws DukeException If no arguments are provided.
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
            throw new DukeException("Parameter must be an argument");
        }
        int index = Integer.parseInt(parts[1]);
        if (index > tasks.getSize() - 1 || index < 0) {
            throw new DukeException("Task does not exist");
        }
//        assert index >= 1 : "Value must be at least 1";
        Task toBeRemoved = tasks.get(index - 1);
        tasks.remove(index - 1);
        return Ui.showDeleteText() + toBeRemoved.toString() + tasks.getSizeString();
    }
}
