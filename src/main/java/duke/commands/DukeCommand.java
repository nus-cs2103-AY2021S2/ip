package duke.command;

/**
 * Valid commands that the user can execute.
 */
public enum DukeCommand {
    BYE,
    DEADLINE,
    DELETE,
    DONE,
    EVENT,
    FIND,
    LIST,
    TODO;

    /**
     * Returns a boolean to indicate whether the user's input is valid.
     *
     * @param value input command from the user
     * @return the boolean to indicate whether is it a valid command
     */
    public static boolean isContains(String value) {
        for (DukeCommand cmd : values()) {
            if (cmd.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
