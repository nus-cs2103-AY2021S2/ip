package owen;

/**
 * Encapsulates the command's type, arguments, and original
 * command string.
 */
public class Command {
    private final CommandType type;
    private final String args;
    private final String original;

    public Command(CommandType type, String args, String original) {
        this.type = type;
        this.args = args;
        this.original = original;
    }

    public CommandType getType() {
        return this.type;
    }

    public String getArgs() {
        return this.args;
    }

    public String getOriginal() {
        return this.original;
    }
}
