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
     * @throws InsufficientArgumentsException If no arguments are provided.
     */
    @Override
    public String execute() throws InsufficientArgumentsException {
        if (parts.length == 1) {
            throw new InsufficientArgumentsException("Insufficient arguments provided");
        }
        String greetingMessage = "Here are the matching tasks in your list";
        StringBuilder keyString = new StringBuilder();
        StringBuilder findStringBuilder = new StringBuilder();
        findStringBuilder.append(greetingMessage).append("\n");
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
        return findStringBuilder.toString();
    }
}
