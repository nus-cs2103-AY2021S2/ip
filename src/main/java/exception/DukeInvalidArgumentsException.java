package exception;

public class DukeInvalidArgumentsException extends DukeException {

    private String command;
    private String error;

    public DukeInvalidArgumentsException(String command, String error) {
        super(String.format("Command %s encountered invalid arguments: %s", command, error));
        this.command = command;
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public String getError() {
        return error;
    }
}
