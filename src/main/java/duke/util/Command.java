package duke.util;

/**
 * Enum for all available commands of duke.
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

    /**
     * Returns format and more info for each command.
     *
     * @return Format and more info for each command.
     */
    public String getHelp() {
        switch(this) {
        case BYE:
            return "bye\n\t- Close Duke";
        case CLEAR:
            return "clear\n\t- Clear all current tasks and start a new tasklist";
        case DEADLINE:
            return "deadline DESCRIPTION /by DATE\n\t- Add a deadline task with a due date (YYYY-MM-DD)";
        case DELETE:
            return "delete INDEX [INDEX...]\n\t- Delete one or more tasks\n\t- eg. (delete 1 2 3)";
        case DONE:
            return "done INDEX [INDEX...]\n\t- Mark one or more tasks as done\n\t- eg. (done 1 2 3)";
        case EVENT:
            return "event DESCRIPTION /at DATE\n\t- Add an event task with a date (YYYY-MM-DD)";
        case HELP:
            return "help\n\t- Display list of commands";
        case HIGHPRIORITY:
            return "highpriority INDEX\n\t- Set this task as high priority";
        case LIST:
            return "list\n\t- List out all task";
        case LOAD:
            return "load\n\t- Load tasklist from saved file";
        case LOWPRIORITY:
            return "lowpriority INDEX\n\t- Set this task as low priority";
        case SAMPLE:
            return "sample\n\t- Load some sample data\n\t- use \"clear\" command to clear all data";
        case SAVE:
            return "save\n\t- save tasklist to \"data/dukeData.txt\"";
        case SEARCH:
            return "search {KEYWORD | DATE}\n\t"
                    + "- Display all task containing the following keyword.\n\t"
                    + "- If the keyword is in a valid date format(YYYY-MM-DD), display all task on that date.";
        case SORT:
            return "sort\n\t- Order tasklist in the following priority\n\t"
                    + "1. High Priority task\n\t2. Incomplete task\n\t"
                    + "3. Todo task\n\t4. Eariler date\n\t5. Lexicographically";
        case TODO:
            return "todo DESCRIPTION\n\t- Add a todo task";
        default:
            assert false;
            return "";
        }
    }
}
