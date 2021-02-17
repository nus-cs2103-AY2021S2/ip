package popo.utils;

/**
 * Container for messages to be shown to user.
 */
public class Messages {
    // Messages to show to user upon a parse error.
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of a task cannot be empty.";
    public static final String MESSAGE_ENTER_COMMAND = "Please enter a command.";
    public static final String MESSAGE_FOLLOW_USAGE = "Please follow the usage as shown below:";
    public static final String MESSAGE_INDICATE_TASK = "Please indicate a task number.";
    public static final String MESSAGE_INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_DURATION = "Please enter a valid number for the duration.";
    public static final String MESSAGE_INVALID_SYNTAX = "Invalid description syntax.";
    public static final String MESSAGE_INVALID_TASK_INDEX = "Please enter a valid task number";
    public static final String MESSAGE_MISSING_SEARCH_WORD = "Please enter a search word or phrase!";

    // Messages to show to user upon successful execution of a command.
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";
    public static final String MESSAGE_DONE_TASK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_EMPTY_TASKLIST = "You do not have anything to do at the moment!";
    public static final String MESSAGE_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String MESSAGE_HELP = "Here is the list of available commands:";
    public static final String MESSAGE_INDEX_TASK_FORMAT = "%d.%s";
    public static final String MESSAGE_NO_MATCHES = "Sorry, there are no matching tasks in your list";
    public static final String MESSAGE_SHOW_TASKLIST = "Here are the tasks in your list:";
    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "This task is already completed!";
    public static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";

    // Message to show to user upon failed execution of a command.
    public static final String MESSAGE_INVALID_INDEX = "Please enter a valid index!";
}
