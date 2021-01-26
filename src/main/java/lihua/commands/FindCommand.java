package lihua.commands;

public class FindCommand extends Command {
    private final String keyWord;
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List down all the tasks containing the key word specified.";

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public CommandResult execute() {
        String message = tasks.listTasks(keyWord);
        return new CommandResult(message);
    }
}
