package duke;

public class DukeNumOutOfRangeException extends DukeException {
    private final String message = "     'done/delete' cmd num input is out of range!\n"
            + "     range to be within N (num of task in list)\n"
            + "     to check N, use 'list' command\n"
            + "     if they are no task in the list,\n"
            + "     do not use done/delete commands!\n";

    /**
     * Returns string message of error.
     *
     * @return String of num out of range error.
     */
    @Override
    public String toString() {
        return message;
    }
}
