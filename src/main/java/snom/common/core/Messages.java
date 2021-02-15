package snom.common.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String SYMBOL_TICK = "\u2713";
    public static final String SYMBOL_BLANK = "\u2007";
    public static final String MESSAGE_WELCOME = "Bonjour! I'm Snom! *squish*\n "
            + "Try giving me some commands, I might be able to do something!\n"
            + "[type 'bye' to exit program]";
    public static final String MESSAGE_EMPTY_TASK_LIST = "You have no task in your list right now, "
            + "try adding some and try again :D";
    public static final String MESSAGE_NO_MATCHING_TASK = "No matching task found.";
    public static final String MESSAGE_TASK_LIST = "Here are the task(s) in your list:\n";
    public static final String MESSAGE_MATCHING_TASK_LIST = "Here are the matching tasks in your list:\n";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n\t%1$s\n"
            + "Now you have %2$d tasks in the list.";
    public static final String MESSAGE_TASK_FINISHED = "Great Job! I've marked this task(s) as finish:\n";
    public static final String MESSAGE_TASK_DELETED = "Noted, I've deleted this task(s)\n";
    public static final String MESSAGE_EXIT = "Ciao! Hope to see you again soon!";
    public static final String ERROR_INVALID_TASK_NUM = "Oops! You have entered a task number: "
            + "%1$d, which is invalid! Please try again!";
    public static final String ERROR_INVALID_MIN_TASK_NUM = "Oops! Please at least give one task number";
    public static final String ERROR_INVALID_MIN_TASK_NUM_TYPE = "Oops! Only integers are valid task numbers!";
    public static final String ERROR_INVALID_DATE_TIME = "Oops! Please enter a valid date time format "
            + "[YYYY-MM-DD HH:MM]";
    public static final String ERROR_INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what %1$s means :-(";
    public static final String ERROR_INVALID_ADD_COMMAND = "Error: Something magical happened while "
            + "Snom trying to create a task!";
    public static final String ERROR_INVALID_MIN_DATE_TIME = "Please enter at least one date!";
    public static final String ERROR_INVALID_MAX_DATE_TIME = "Oops! You have entered more than ONE date, "
            + "please try again!";
    public static final String ERROR_INVALID_INT_INPUT = "Oops! You have entered a non Integer value!";
    public static final String ERROR_INVALID_DOUBLE_INPUT = "Oops! You have entered a non Double value!";
}
