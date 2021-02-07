package lihua.commands;

/**
 * Command class representing a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    /** Command help information for help command */
    public static final String MESSAGE_USAGE = "find: List down all the tasks containing the key word specified.";
    /** Keyword to find in tasks */
    private final String keyWord;

    /**
     * Initializes a new FindCommand with a keyword.
     *
     * @param keyWord The keyword to find in tasks.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command. Gathers the tasks containing the keyword.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    public CommandResult execute() {
        String message = tasks.listTasks(keyWord);
        return new CommandResult(message);
    }
}
