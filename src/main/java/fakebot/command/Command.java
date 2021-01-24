package fakebot.command;

import java.util.Locale;


//Store Command Entered by user
public class Command {
    private CommandType type;
    private String description;

    public Command(CommandType type) {
        this.type = type;
    }

    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Get Command Type.
     * @return CommandType of the Command.
     */
    public CommandType getCommand() {
        return type;
    }

    /**
     * Get Command Description Type.
     * @return Description of the Command.
     */
    public String getDescription() {
        return description;
    }

}
