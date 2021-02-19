package lihua.commands.feedback;

/**
 * Command Result class representing the result and feedback after the command is executed.
 */
public class CommandResult {
    /** The feedback to user */
    private final String feedbackToUser;
    private boolean isUserCommandError = false;

    /**
     * Initializes a CommandResult object with a feedback.
     *
     * @param feedbackToUser The feedback to user.
     */
    public CommandResult(String feedbackToUser) {
        assert feedbackToUser != null;
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Initializes a CommandResult object with a feedback.
     *
     * @param feedbackToUser The feedback to user.
     */
    public CommandResult(String feedbackToUser, boolean isUserCommandError) {
        assert feedbackToUser != null;
        this.feedbackToUser = feedbackToUser;
        this.isUserCommandError = isUserCommandError;
    }

    /**
     * Retrieve the feedback for user.
     *
     * @return The feedback for user.
     */
    public String getFeedBack() {
        assert feedbackToUser != null;
        return feedbackToUser;
    }

    /**
     * Check whether the instance is resulted from user's error in commands.
     *
     * @return True if the instance is resulted from user's error in commands.
     */
    public boolean isUserCommandError() {
        return isUserCommandError;
    }
}
