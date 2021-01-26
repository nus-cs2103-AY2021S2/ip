package exception;

public class MikeCommandExecutionException extends Exception {
    private String failedCommand;
    /**
     * Execution to be thrown when an error in command execution has occurred
     */
    public MikeCommandExecutionException(String failedCommand, String errorMessage) {
        super(errorMessage);
        this.failedCommand = failedCommand;
    }

    public String getCommand() {
        return this.failedCommand;
    }
}
