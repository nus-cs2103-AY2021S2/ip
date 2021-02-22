package quackers.command;

/**
 * Represents the response of a command execution.
 */
public class CommandResponse {

    private Class<? extends Command> cls;
    private String message;
    private boolean toExit;

    /**
     * Constructs a CommandResponse object.
     *
     * @param cls
     * @param message Response message.
     * @param toExit Terminate program after command execution.
     */
    public CommandResponse(Class<? extends Command> cls, String message, boolean toExit) {
        this.cls = cls;
        this.message = message;
        this.toExit = toExit;
    }

    /**
     * Returns the class type of the command.
     *
     * @return Command class type.
     */
    public Class<? extends Command> getCommandClass() {
        return this.cls;
    }

    /**
     * Returns a boolean to check if the program terminates after command execution.
     *
     * @return Command class type.
     */
    public boolean canExit() {
        return this.toExit;
    }

    /**
     * Returns the response message.
     *
     * @return Response message.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
