package duke.command;

/**
 * Represents a user command.
 */
public abstract class Command {
    String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    /**
     * Executes the command.
     */
    public abstract void run();

}
