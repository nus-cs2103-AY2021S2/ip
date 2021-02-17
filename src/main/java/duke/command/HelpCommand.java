package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class HelpCommand implements Command {
    private static final String HELP_ALL_RESPONSE = "List of commands:\n"
            + "1. todo\n2. deadline\n3. event\n4. list\n5. done\n6.delete\n7. find\n8. check\n"
            + "Type 'help <command>' in the chat to see the details!";
    private static final String HELP_TODO_RESPONSE = "Adds a todo task to the list\n\nFormat: todo <task name>";
    private static final String HELP_DEADLINE_RESPONSE = "Adds a deadline task to the list\n\n"
            + "Format: deadline <task name> /by <date in YYYY-MM-DD format>";
    private static final String HELP_EVENT_RESPONSE = "Adds an event task to the list\n\n"
            + "Format: event <task name> /at <date in YYYY-MM-DD format>";
    private static final String HELP_LIST_RESPONSE = "Lists all the events currently in the list\n\nFormat: list";
    private static final String HELP_DONE_RESPONSE = "Marks the task as done\n\nFormat: done <task number>";
    private static final String HELP_DELETE_RESPONSE = "Deletes the task\n\nFormat: delete <task number>";
    private static final String HELP_FIND_RESPONSE = "Finds a task that contains the search term\n\nFormat: find <search term>";
    private static final String HELP_CHECK_RESPONSE = "Checks all tasks on a specific date\n\n"
            + "Format: check <date in YYYY-MM-DD format>";

    private String helpResponse;

    private HelpCommand(String helpResponse) {
        this.helpResponse = helpResponse;
    }

    // static help command constructor
    public static HelpCommand getHelpAllCommand() {
        return new HelpCommand(HELP_ALL_RESPONSE);
    }

    public static HelpCommand getHelpToDoCommand() {
        return new HelpCommand(HELP_TODO_RESPONSE);
    }

    public static HelpCommand getHelpDeadlineCommand() {
        return new HelpCommand(HELP_DEADLINE_RESPONSE);
    }

    public static HelpCommand getHelpEventCommand() {
        return new HelpCommand(HELP_EVENT_RESPONSE);
    }

    public static HelpCommand getHelpListCommand() {
        return new HelpCommand(HELP_LIST_RESPONSE);
    }

    public static HelpCommand getHelpDoneCommand() {
        return new HelpCommand(HELP_DONE_RESPONSE);
    }

    public static HelpCommand getHelpDeleteCommand() {
        return new HelpCommand(HELP_DELETE_RESPONSE);
    }

    public static HelpCommand getHelpFindCommand() {
        return new HelpCommand(HELP_FIND_RESPONSE);
    }

    public static HelpCommand getHelpCheckCommand() {
        return new HelpCommand(HELP_CHECK_RESPONSE);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponString(TaskList tasks, Storage storage) {
        return helpResponse;
    }
}
