package lihua.commands;

import java.time.LocalDate;

import lihua.commands.feedback.CommandResult;
import lihua.commons.enums.ListTagCode;


/**
 * Command class representing a command to list tasks.
 */
public class ListCommand extends Command {
    /** Command help information for list command */
    public static final String MESSAGE_USAGE = "list: List down all the tasks. "
            + "List down all the tasks on a specific date, if additional date argument is given\n"
            + "List down all time-related tasks in a chronological order, if additional tag -t is given.\n"
            + "---- Example 1: list\n"
            + "---- Example 2: list [yyyy-mm-dd]\n"
            + "---- Example 3: list -time";
    /** Optional argument indicating the date of tasks to be listed */
    private final LocalDate date;
    private ListTagCode tagCode = ListTagCode.NORMAL;

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
        date = null;
    }

    /**
     * Initializes a ListCommand with a ListTagCode Enum Object.
     *
     * @param code A listTagCode Enum object specifying the type of tag the command is receiving.
     */
    public ListCommand(ListTagCode code) {
        date = null;
        tagCode = code;
    }

    /**
     * Executes the list command.
     * List all the tasks or all the tasks on a specific date.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        String message = tasks.listTasks(date, tagCode);
        assert message != null;
        assert !message.equals(" ");
        return new CommandResult(message);
    }
}
