package duke;

/**
 * Represents actions that can be used on DukeObjects.Duke.
 * Actions are the first word in user input commands.
 */
public enum DukeActions {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    /**
     * The String form of each action.
     */
    public final String str;

    DukeActions(String str) {
        this.str = str;
    }
}
