/**
 * {@code Command} is an enumeration of the possible commands.
 */
public enum Command {
    DONE("done"), TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    LIST("list"), DELETE("delete"), BYE("bye"), UNKNOWN("");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    /**
     * Reads in string and transforms to command.
     *
     * @param string String form of command.
     * @return The command
     */
    public static Command command(String string) {
        String command = string.toLowerCase();
        for (Command c : Command.values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        return UNKNOWN;
    }

}
