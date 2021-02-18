package duke.commons.core;

public class Messages {
    public static final String MESSAGE_ERROR = "OOPS! %1$s";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found.";
    public static final String MESSAGE_SAVE_FILE_ERROR = "Error saving file.";
    public static final String MESSAGE_COMMAND_NOT_FOUND = "I'm sorry, but I don't know what that means.";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Your command is not recognized!";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke, your personal assistant!"
            + "\nWhat can I do for you today?";
    public static final String MESSAGE_TASK_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_ALL_DONE = "Nice! I've marked the following task(s) as done:";
    public static final String MESSAGE_TASK_DELETE = "Noted. I've removed the following task(s):";
    public static final String MESSAGE_TASK_ALL_DELETE = "Noted. I've removed all your tasks:";
    public static final String MESSAGE_TASKLIST_SUMMARY = "Now you have %d %s in the list.%n";
    public static final String MESSAGE_LIST = "Here are the %s in your list:%n";
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "The index you have entered is out of bound.";
    public static final String MESSAGE_TASKLIST_EMPTY = "Your task list is empty.";
    public static final String MESSAGE_NO_SEARCH_RESULT = "No search results found.";
    public static final String MESSAGE_TASK_COMPLETED = "You have already completed this task!";
    public static final String MESSAGE_TASK_ALL_COMPLETED = "You have already completed all the tasks!";
    public static final String MESSAGE_INVALID_DATETIME_FORMAT = "You have entered an invalid date time format.";
    public static final String MESSAGE_INVALID_TASK_FORMAT = "You have entered an invalid %1$s format.%n%2$s";
    public static final String MESSAGE_CORRECT_EVENT_FORMAT = "Event Format: event DESCRIPTION /at DATETIME";
    public static final String MESSAGE_CORRECT_DEADLINE_FORMAT = "Deadline Format: deadline DESCRIPTION /by DATETIME";
    public static final String MESSAGE_EMPTY_TASK_DESCRIPTION = "The description of %1$s cannot be empty.";
    public static final String MESSAGE_EMPTY_DATETIME_DESCRIPTION = "The date and time of %1$s cannot be empty.";
    public static final String MESSAGE_TASK_DUPLICATE = "You have already inserted this task.";
    public static final String MESSAGE_TASK_ANOMALIES = "There is a timing clash with existing tasks.";
    private static final String MESSAGE_HELP_FORMAT = "%s %s %n";
    public static final String MESSAGE_HELP = "These are the commands you can use:\n"
            + String.format(MESSAGE_HELP_FORMAT, "todo         ", "todo DESCRIPTION")
            + String.format(MESSAGE_HELP_FORMAT, "deadline  ", "deadline DESCRIPTION /by DATE[TIME]")
            + String.format(MESSAGE_HELP_FORMAT, "event       ", "event DESCRIPTION /at DATE[TIME]")
            + String.format(MESSAGE_HELP_FORMAT, "list            ", "list [DATE]")
            + String.format(MESSAGE_HELP_FORMAT, "find          ", "find KEYWORD [MORE KEYWORDS]")
            + String.format(MESSAGE_HELP_FORMAT, "done        ", "done ... or done all")
            + String.format(MESSAGE_HELP_FORMAT, "delete      ", "delete ... or delete all")
            + String.format(MESSAGE_HELP_FORMAT, "bye          ", "bye");
}
