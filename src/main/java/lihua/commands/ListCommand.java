package lihua.commands;

import java.time.LocalDate;

/**
 * Command class representing a command to list tasks.
 */
public class ListCommand extends Command {
    /** Command help information for list command */
    public static final String MESSAGE_USAGE = "list: List down all the tasks. "
            + "List down all the tasks on a specific date, if additional date argument is given\n"
            + "---- Example 1: list\n"
            + "---- Example 2: list [yyyy-mm-dd]";
    /** Optional argument indicating the date of tasks to be listed */
    private final LocalDate date;

    /**
     * Initializes a new ListCommand with a LocalDate.
     *
     * @param date The date on which the tasks should be listed.
     */
    public ListCommand(LocalDate date) {
        super();
        this.date = date;
    }

    /**
     * Initializes a default ListCommand, listing all existing tasks.
     */
    public ListCommand() {
        super();
        this.date = null;
    }

    /**
     * Executes the list command.
     * List all the tasks or all the tasks on a specific date.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        String message = tasks.listTasks(date);
        assert message != null;
        assert !message.equals(" ");
        return new CommandResult(message);
    }
}
