package fakebot.command;

/**
 * Command Class.
 */
public class Command {
    private CommandType type;
    private String description;

    /**
     * Class constructor specifying the command type.
     */
    public Command(CommandType type) {
        this.type = type;
    }


    /**
     * Class constructor specifying the command type and description.
     */
    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Returns Command Type.
     *
     * @return CommandType of the Command.
     */
    public CommandType getCommand() {
        return type;
    }

    /**
     * Returns Command Description Type.
     *
     * @return Description of the Command.
     */
    public String getDescription() {
        return description;
    }

}
