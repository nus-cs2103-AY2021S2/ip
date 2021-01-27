package duke.utils;

public class Messages {
    public static final String MESSAGE_EXIT = "Exiting...";
    
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";
    public static final String MESSAGE_DONE_TASK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String MESSAGE_NO_MATCHES = "Sorry, there are no matching tasks in your list";

    public static final String MESSAGE_INDEX_TASK_FORMAT = "%d.%s";
    public static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";

    public static final String MESSAGE_INVALID_INDEX = "Please enter a valid index!";
    
    public static final String MESSAGE_EMPTY_TASKLIST = "You do not have anything to do at the moment!";
    public static final String MESSAGE_SHOW_TASKLIST = "Here are the tasks in your list:";
    
    public static final String MESSAGE_HELP = "Here are the list of available commands:\n"
            + "BYE:\nExit the program\nUsage: bye\n"
            + "LIST:\nPrint the list of current tasks\nUsage: list\n"
            + "DONE:\nMark a task as completed\nUsage: done <task_number>\n"
            + "DELETE:\nDelete a task\nUsage: delete <task_number>\n"
            + "TODO:\nAdd a todo task\nUsage: todo <task_description>\n"
            + "DEADLINE:\nAdd a deadline task\nUsage: deadline <task_description> /by dd/mm/yyyy HHHH\n"
            + "EVENT:\nAdd an event task\nUsage: event <task_description> /at <event_time>\n"
            + "HELP:\nPrint available commands\nUsage: help";
}
