package duke.command;

public abstract class Command {
    protected final String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
