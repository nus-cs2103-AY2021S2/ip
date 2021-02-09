package duke.commands;

import java.util.Arrays;

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
        return Arrays.stream(values()).anyMatch(cmd -> cmd.name().equals(value));
    }

    public String toLower() {
        return this.toString().toLowerCase();
    }
}
