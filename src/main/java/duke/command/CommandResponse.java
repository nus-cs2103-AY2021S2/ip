package duke.command;

public class CommandResponse {

    private Class<? extends Command> cls;
    private String message;
    private boolean toExit;

    public CommandResponse(Class<? extends Command> cls,
                           String message, boolean toExit) {
        this.cls = cls;
        this.message = message;
        this.toExit = toExit;
    }

    public Class<? extends Command> getCommandClass() {
        return this.cls;
    }

    public boolean canExit() {
        return this.toExit;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
