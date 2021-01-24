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

    //Get Command Type
    public CommandType getCommand() {
        return type;
    }

    //Get Command DDescription
    public String getDescription() {
        return description;
    }

    //Get Command Name mainly for Printing
    public String getCommandName() {
        return type.toString().toLowerCase(Locale.ROOT);
    }
}
