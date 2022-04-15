package duke.common;

/**
 * A utility class that consolidates strings used by Duke.
 */
public final class DukeString {
    public static final String SEPARATOR = "--------------------------------------\n";
    public static final String SEPARATOR_ERR = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";

    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MESSAGE_BYE = "Goodbye!";
    public static final String MESSAGE_DONE = "Nice! I've marked this as done: ";
    public static final String MESSAGE_ADDED = "Got it. I've added this task:\n\t%s\nNow you have %d tasks.";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n";
    public static final String MESSAGE_LIST_EMPTY = "You have no tasks.";
    public static final String MESSAGE_DELETE = "Got it. I've removed this task:\n\t%s\nNow you have %d tasks.";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:\n";
    public static final String MESSAGE_NONE_FOUND = "Sorry, I did not find any tasks matching %s.";
    public static final String MESSAGE_SNOOZED_TASK = "Got it. I've snoozed this task:\n\t%s";

    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DEADLINE_SEP = "/by";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_EVENT_SEP = "/at";
    public static final String COMMAND_EVENT_TO = "/to";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_SNOOZE = "snooze";
    public static final String COMMAND_SNOOZE_REGEX = COMMAND_DEADLINE_SEP + '|' + COMMAND_EVENT_SEP;

    public static final String FORMAT_DATE_INPUT = "dd-MM-yy HHmm";
    public static final String FORMAT_DATE_OUTPUT = "dd LLL uu hhmma";
    public static final String FORMAT_DEADLINE = "[D]%s (by: %s)";
    public static final String FORMAT_EVENT = "[E]%s (from: %s to %s)";
    public static final String FORMAT_TODO = "[T]%s";

    public static final String EXCEPTION_INVALID_COMMAND = "Sorry, I did not understand that.";
    public static final String EXCEPTION_EMPTY_DESCRIPTION = "Sorry, the description of a %s cannot be empty.";
    public static final String EXCEPTION_INVALID_TASK = "Sorry, I could not find that task.";
    public static final String EXCEPTION_INVALID_DATE =
            "Sorry, I did not understand that date. Please use dd-MM-yy HHmm.";
    public static final String EXCEPTION_EMPTY_DEADLINE_DATE = "Sorry, the date of a deadline must be specified.";
    public static final String EXCEPTION_EMPTY_EVENT_DATE =
            "Sorry, the start and end date of an event must be specified.";
    public static final String EXCEPTION_INVALID_EVENT_END =
            "Sorry, the end date of an event must be after the start date.";
    public static final String EXCEPTION_INVALID_DATE_FOR_TASK =
            "Sorry, the task\n\t%s\ndoes not support these dates.";
    public static final String EXCEPTION_STORAGE_READ_ERROR =
            "Sorry, there has been an error reading your tasks.\nMaking a copy of your tasks at data/duke.bak.";
    public static final String EXCEPTION_STORAGE_WRITE_ERROR = "Sorry, there has been an error writing your tasks.";

    private DukeString() {

    }
}
