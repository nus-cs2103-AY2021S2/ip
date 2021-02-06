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
     * Returns command cype.
     *
     * @return a CommandType of the command.
     */
    public CommandType getCommand() {
        return type;
    }

    /**
     * Returns command descriptions.
     *
     * @return a string containing descriptions of the command.
     */
    public String[] getDescriptions() {
        return descriptions;
    }

    /**
     * Returns command description.
     *
     * @return a string containing description of the command.
     */
    public String getDescription() {
        return descriptions[0];
    }

}
