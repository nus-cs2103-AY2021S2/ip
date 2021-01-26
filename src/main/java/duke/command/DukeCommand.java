package duke.command;

public enum DukeCommand {
    BYE,
    DEADLINE,
    DELETE,
    DONE,
    EVENT,
    LIST,
    TODO;

    /**
     * This method checks whether the user's input is a valid command.
     * @param value input command from the user.
     * @return the boolean to indicate whether is it a valid command.
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
