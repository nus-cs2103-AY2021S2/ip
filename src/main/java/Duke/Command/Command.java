package Duke.Command;

/**
 * An enum containing all available commands for Duke.
 */
public enum Command {
    LIST("list"), BYE("bye"),
    DONE("done"), DELETE("delete"), FIND("find"),
    EVENT("event"), DEADLINE("deadline"), TODO("todo");

    /**
     * The Command e-num constructor has 1 parameter: The string representation for the command.
     * @param action A string that is associated with the command.
     */
    private final String action;

    Command(String action) {
        this.action = action;
    }

    /**
     * Gets the action related to the command.
     * @return The action related to the command.
     */
    public String getAction() {
        return this.action;
    }
}
