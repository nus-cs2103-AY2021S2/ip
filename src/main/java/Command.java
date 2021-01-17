/**
 * {@code Command} is an enumeration of the possible commands.
 */
public enum Command {
    DONE, TODO, DEADLINE, EVENT, LIST, DELETE, UNKNOWN;

    /**
     * Reads in string and transforms to command.
     * @param string String form of command.
     * @return The command
     */
    public static Command command(String string) {
        if (string.equalsIgnoreCase("list")) {
            return LIST;
        } else if (string.equalsIgnoreCase("done")) {
            return DONE;
        } else if (string.equalsIgnoreCase("delete")) {
            return DELETE;
        } else if (string.equalsIgnoreCase("todo")) {
            return TODO;
        } else if (string.equalsIgnoreCase("deadline")) {
            return DEADLINE;
        } else if (string.equalsIgnoreCase("event")) {
            return EVENT;
        } else {
            return UNKNOWN;
        }
    }
}
