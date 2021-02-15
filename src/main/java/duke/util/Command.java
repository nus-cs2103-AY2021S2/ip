package duke.util;

/**
 * Enum for all commands of duke.
 */
public enum Command {
    BYE,
    CLEAR,
    DEADLINE,
    DELETE,
    DONE,
    EVENT,
    HELP,
    HIGHPRIORITY,
    LIST,
    LOAD,
    LOWPRIORITY,
    SAMPLE,
    SAVE,
    SEARCH,
    SORT,
    TODO;

    public String getHelp() {
        switch(this) {
        case BYE:
            return "bye\n\t- Close Duke";
        case CLEAR:
            return "clear\n\t- Clear all current tasks and start a new tasklist";
        case DEADLINE:
            return "deadline [description] /by [date]\n\t- Add a deadline task with a due date (YYYY-MM-DD)";
        case DELETE:
            return "delete [int (int int...)]\n\t- Delete one or more tasks\n\t- eg. (delete 1 2 3)";
        case DONE:
            return "done [int (int int...)]\n\t- Mark one or more tasks as done\n\t- eg. (done 1 2 3)";
        case EVENT:
            return "event [description] /at [date]\n\t- Add an event task with a date (YYYY-MM-DD)";
        case HELP:
            return "help\n\t- Display list of commands";
        case HIGHPRIORITY:
            return "highpriority [int]\n\t- Set this task as high priority";
        case LIST:
            return "list\n\t- List out all task";
        case LOAD:
            return "load\n\t- Load tasklist from saved file";
        case LOWPRIORITY:
            return "lowpriority [int]\n\t- Set this task as low priority";
        case SAMPLE:
            return "sample\n\t- Load some sample data\n\t- use \"new\" command to clear all data";
        case SAVE:
            return "save\n\t- save tasklist to \"data/dukeData.txt\"";
        case SEARCH:
            return "search [keyword | date]\n\t"
                    + "- Display all task containing the following keyword.\n\t"
                    + "- If the keyword is in a valid date format(YYYY-MM-DD), display all task on that date.";
        case SORT:
            return "sort\n\t- Order tasklist in the following priority\n\t"
                    + "1. Incomplete task\n\t2. Todo task\n\t3. Eariler date\n\t4. lexicographically";
        case TODO:
            return "todo [description]\n\t- Add a todo task";
        default:
            assert false;
            return "";
        }
    }
}
