package duke.command;

import duke.logic.Storage;
import duke.task.TaskList;

/**
 * Represents a task telling Duke to help the user
 */
public class HelpCommand implements Command {
    private static final String HELP_ALL_RESPONSE = "List of commands:\n"
            + "1. todo\n2. deadline\n3. event\n4. list\n5. done\n6.delete\n7. find\n8. check\n9. exit\n"
            + "Type 'help <command>' in the chat to see the details!";
    private static final String HELP_TODO_RESPONSE = "Adds a todo task to the list\n\nFormat: todo <task name>";
    private static final String HELP_DEADLINE_RESPONSE = "Adds a deadline task to the list\n\n"
            + "Format: deadline <task name> /by <date in YYYY-MM-DD format>";
    private static final String HELP_EVENT_RESPONSE = "Adds an event task to the list\n\n"
            + "Format: event <task name> /at <date in YYYY-MM-DD format>";
    private static final String HELP_LIST_RESPONSE = "Lists all the events currently in the list\n\nFormat: list";
    private static final String HELP_DONE_RESPONSE = "Marks the task as done\n\nFormat: done <task number>";
    private static final String HELP_DELETE_RESPONSE = "Deletes the task\n\nFormat: delete <task number>";
    private static final String HELP_FIND_RESPONSE = "Finds a task that contains the search term\n\n"
            + "Format: find <search term>";
    private static final String HELP_CHECK_RESPONSE = "Checks all tasks on a specific date\n\n"
            + "Format: check <date in YYYY-MM-DD format>";
    private static final String HELP_EXIT_RESPONSE = "Exits duke\n\nFormat: exit";

    private String helpResponse;

    private HelpCommand(String helpResponse) {
        this.helpResponse = helpResponse;
    }

    // static help command constructor
    /**
     * Static constructor for generic help command
     * @return Help command with generic help response
     */
    public static HelpCommand getHelpAllCommand() {
        return new HelpCommand(HELP_ALL_RESPONSE);
    }

    /**
     * Static constructor for todo help command
     * @return Help command with help regarding the todo task
     */
    public static HelpCommand getHelpToDoCommand() {
        return new HelpCommand(HELP_TODO_RESPONSE);
    }

    /**
     * Static constructor for deadline help command
     * @return Help command with help regarding the deadline task
     */
    public static HelpCommand getHelpDeadlineCommand() {
        return new HelpCommand(HELP_DEADLINE_RESPONSE);
    }

    /**
     * Static constructor for event help command
     * @return Help command with help regarding the event task
     */
    public static HelpCommand getHelpEventCommand() {
        return new HelpCommand(HELP_EVENT_RESPONSE);
    }

    /**
     * Static constructor for list help command
     * @return Help command with help regarding the list command
     */
    public static HelpCommand getHelpListCommand() {
        return new HelpCommand(HELP_LIST_RESPONSE);
    }

    /**
     * Static constructor for done help command
     * @return Help command with help regarding the done command
     */
    public static HelpCommand getHelpDoneCommand() {
        return new HelpCommand(HELP_DONE_RESPONSE);
    }

    /**
     * Static constructor for delete help command
     * @return Help command with help regarding the delete command
     */
    public static HelpCommand getHelpDeleteCommand() {
        return new HelpCommand(HELP_DELETE_RESPONSE);
    }

    /**
     * Static constructor for find help command
     * @return Help command with help regarding the find command
     */
    public static HelpCommand getHelpFindCommand() {
        return new HelpCommand(HELP_FIND_RESPONSE);
    }

    /**
     * Static constructor for check help command
     * @return Help command with help regarding the check command
     */
    public static HelpCommand getHelpCheckCommand() {
        return new HelpCommand(HELP_CHECK_RESPONSE);
    }

    /**
     * Static constructor for exit help command
     * @return Help command with help regarding the exit command
     */
    public static HelpCommand getHelpExitCommand() {
        return new HelpCommand(HELP_EXIT_RESPONSE);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return helpResponse;
    }
}
