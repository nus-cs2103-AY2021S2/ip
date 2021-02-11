package duke.commands;

/**
 * Represents the result of executing a command.
 *
 * @author Benedict Khoo
 */
public class CommandResult {
    protected String feedback;
    protected boolean isExit;
    protected boolean isError;

    /**
     * Constructs a CommandResult with the given feedback message and a signal to Duke not to exit.
     *
     * @param feedback The feedback message for the user.
     */
    public CommandResult(String feedback) {
        this(feedback, false);
    }

    /**
     * Constructs a CommandResult with the given feedback message and a signal to Duke to exit or not.
     *
     * @param feedback The feedback message for the user.
     * @param isExit   True if Duke should exit after the current command. False otherwise.
     */
    public CommandResult(String feedback, boolean isExit) {
        this(feedback, isExit, false);
    }

    /**
     * Constructs a CommandResult with the given feedback message, signal to Duke to exit or not, and
     * flag indicating whether the command terminated with an error or not.
     *
     * @param feedback The feedback message for the user.
     * @param isExit   True if Duke should exit after the current command. False otherwise.
     * @param isError  True if this command did not complete successfully. False otherwise.
     */
    public CommandResult(String feedback, boolean isExit, boolean isError) {
        this.feedback = feedback;
        this.isExit = isExit;
        this.isError = isError;
    }

    /**
     * Returns the feedback message.
     *
     * @return The feedback message.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Returns true if there is feedback for the user. False otherwise.
     *
     * @return True if there is feedback for the user. False otherwise.
     */
    public boolean hasFeedback() {
        return feedback != null;
    }

    /**
     * Returns true if this command terminated with failure. False otherwise.
     *
     * @return True if this command terminated with failure. False otherwise.
     */
    public boolean isError() {
        return isError;
    }

    /**
     * Returns true if Duke should exit. False otherwise.
     *
     * @return True if Duke should exit. False otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
