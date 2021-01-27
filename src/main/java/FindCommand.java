public class FindCommand extends Command {

    public static final String COMMAND = "find";
    private final String keyword;
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a keyword to search for.\n\tCommand: find [keyword]";

    private FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public String execute() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:\n");
        int numbering = 1;
        for (Task task: taskList.getList()) {
            if (task.containsText(keyword)) {
                builder.append("\t").append(numbering++).append(".").append(task).append("\n");
            }
        }

        if (numbering == 1) {
            builder.append("\tYou currently have 0 tasks.");
        }
        return builder.toString().trim();
    }

    public static FindCommand parseArguments(String input) throws DukeException {
        if (input.trim().equals("")) {
            throw new DukeException(ERROR_MESSAGE);
        }
        return new FindCommand(input);
    }
}
