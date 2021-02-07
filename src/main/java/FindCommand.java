public class FindCommand extends Command {

    FindCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

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
