package main.java.duke;

public class DukeCommand {
    private final Command command;
    private final String details;

    public DukeCommand(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    public Command getCommand() {
        return command;
    }

    public String getDetails() {
        return details;
    }
}
