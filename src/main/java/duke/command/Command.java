package duke.command;

public abstract class Command {
    String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public abstract void run();

}
