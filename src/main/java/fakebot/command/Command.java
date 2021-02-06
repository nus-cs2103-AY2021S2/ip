package fakebot.command;

/**
 * Command Class.
 */
public class Command {
    private CommandType type;
    private String[] descriptions;

    /**
     * Class constructor specifying the command type.
     */
    public Command(CommandType type) {
        this.type = type;
    }


    /**
     * Class constructor specifying the command type and description.
     */
    public Command(CommandType type, String[] descriptions) {
        this.type = type;
        this.descriptions = descriptions;
    }

    /**
     * Class constructor specifying the command type and description.
     */
    public Command(CommandType type, String description) {
        this.type = type;
        descriptions = new String[]{description};
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
     * Returns Command Descriptions.
     *
     * @return Descriptions of the Command.
     */
    public String[] getDescriptions() {
        return descriptions;
    }

    /**
     * Returns Command Description.
     *
     * @return Description of the Command.
     */
    public String getDescription() {
        return descriptions[0];
    }

}
