/**
 * Handles the find command, where the user searches for tasks based on provided keywords.
 */
public class FindCommand extends Command {

    FindCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    /**
     * Returns a string representation of the Tasks after they have been found and
     * matched with the given criteria.
     *
     * @return A string representation of one or more tasks that matches the user's requirements.
     * @throws DukeException If no arguments are provided.
     */
    @Override
    public String execute() throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Insufficient arguments provided");
        }
        assert tasks != null : "tasks cannot be null";
        StringBuilder keyString = new StringBuilder();
        StringBuilder findStringBuilder = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            keyString.append(" ");
            keyString.append(parts[i]);
        }
        String findString = keyString.toString();
        for (Task t: TaskList.getTasklist()) {
            if (t.toString().contains(findString)) {
                findStringBuilder.append(t.toString()).append("\n");
            }
        }
        return Ui.showFindText() + findStringBuilder.toString();
    }
}
