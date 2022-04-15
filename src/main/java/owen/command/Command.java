package owen.command;

/**
 * Encapsulates the command's type, arguments, and original
 * command string.
 */
public class Command {
    private final CommandType type;
    private final String args;
    private final String original;

    /**
     * Creates new Command.
     *
     * @param type Type of the command.
     * @param args Argument string to the command.
     * @param original Original command string.
     */
    public Command(CommandType type, String args, String original) {
        this.type = type;
        this.args = args;
        this.original = original;
    }

    /**
     * Gets type of this command.
     *
     * @return Type of this command.
     */
    public CommandType getType() {
        return this.type;
    }

    /**
     * Gets argument string to this command.
     *
     * @return Argument string to this command.
     */
    public String getArgs() {
        return this.args;
    }

    /**
     * Gets original command string.
     *
     * @return Original command string.
     */
    public String getOriginal() {
        return this.original;
    }
}
