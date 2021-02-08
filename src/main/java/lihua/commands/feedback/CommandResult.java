package lihua.commands.feedback;

/**
 * Command Result class representing the result and feedback after the command is executed.
 */
public class CommandResult {
    /** The feedback to user */
    private final String feedbackToUser;

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
     * Retrieve the feedback for user.
     *
     * @return The feedback for user.
     */
    public String getFeedBack() {
        assert feedbackToUser != null;
        return feedbackToUser;
    }
}
