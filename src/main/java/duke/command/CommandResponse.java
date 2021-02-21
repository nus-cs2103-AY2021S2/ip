package duke.command;

public class CommandResponse {

    private String message;
    private boolean toExit;

    public CommandResponse(String message, boolean toExit) {
        this.message = message;
        this.toExit = toExit;
    }

    public boolean canExit() {
        return this.toExit;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
