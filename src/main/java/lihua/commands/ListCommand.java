package lihua.commands;

import java.time.LocalDate;

public class ListCommand extends Command {
    private final LocalDate date;
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List done all the tasks. "
            + "List done all the tasks on a specific date, if additional date argument is given\n"
            + "---- Example 1: " + COMMAND_WORD + "\n"
            + "---- Example 2: " + COMMAND_WORD + " [yyyy-mm-dd]";

    public ListCommand(LocalDate date) {
        super();
        this.date = date;
    }

    public ListCommand() {
        super();
        this.date = null;
    }

    @Override
    public CommandResult execute() {
        // Let lihua.tasks.Tasks check if it is null or not, then perform relevant operations.
        String message = tasks.listTasks(date);
        return new CommandResult(message);
    }
}
