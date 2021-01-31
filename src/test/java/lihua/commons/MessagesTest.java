package lihua.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessagesTest {
    public static final String MESSAGE_HELLO_EXPECTED =
            "Hello! My name is Lihua.\n"
                    + "What can I do for you today? (=V=)";
    public static final String MESSAGE_GET_HELP_EXPECTED =
            "Sorry, I do not understand your command :')\n"
                    + "If you are stuck, type 'help' to get a list of operations available.";
    public static final String MESSAGE_LINE_EXPECTED =
            "-------------------------------------------------";
    public static final String MESSAGE_INFORM_EXIT_EXPECTED =
            "Exiting Lihua as requested...\n"
                    + "Goodbye! Hope to see you again soon! (=V=)";
    public static final String MESSAGE_REPORTING_ADDING_FAILURE_EXPECTED =
            "Sorry, the task adding failed. :( Please check your storage devices.";
    public static final String MESSAGE_VIEW_COMMANDS_EXPECTED =
            "No worries! Here are the commands available:\n"
                    + "todo/deadline/event: add a task of a specific type to the task list.\n"
                    + "---- Example 1: todo [task name]\n" + "---- Example 2: deadline [task name] /by [yyyy-mm-dd]\n"
                    + "---- Example 3: event [task name] /at [yyyy-mm-dd]"
                    + "\n" + "done: Get a specific task done.\n"
                    + "---- Example: done [valid index number]"
                    + "\n" + "delete: Remove a specific task from the list.\n"
                    + "---- Example: delete [valid index number]"
                    + "\n" + "list: List down all the tasks. "
                    + "List down all the tasks on a specific date, if additional date argument is given\n"
                    + "---- Example 1: list\n"
                    + "---- Example 2: list [yyyy-mm-dd]"
                    + "\n" + "find: List down all the tasks containing the key word specified."
                    + "\n" + "help: Shows application usage instructions.\n"
                    + "---- Example: help"
                    + "\n" + "bye: Exit the application. Data will be auto-saved.\n"
                    + "---- Example: bye";
    public static final String MESSAGE_REPORTING_INVALID_INDEX_EXPECTED =
            "Sorry, the index you specified does not exist :'), "
                    + "please type 'list' to see available indices to delete.";
    @Test
    public void messagesContent_noGivenInput_contentMatches() {
        assertEquals(MESSAGE_HELLO_EXPECTED, Messages.MESSAGE_HELLO);
        assertEquals(MESSAGE_GET_HELP_EXPECTED, Messages.MESSAGE_GET_HELP);
        assertEquals(MESSAGE_LINE_EXPECTED, Messages.MESSAGE_LINE);
        assertEquals(MESSAGE_INFORM_EXIT_EXPECTED, Messages.MESSAGE_INFORM_EXIT);
        assertEquals(MESSAGE_REPORTING_ADDING_FAILURE_EXPECTED, Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        assertEquals(MESSAGE_VIEW_COMMANDS_EXPECTED, Messages.MESSAGE_VIEW_COMMANDS);
        assertEquals(MESSAGE_REPORTING_INVALID_INDEX_EXPECTED, Messages.MESSAGE_REPORTING_INVALID_INDEX);
    }
}
