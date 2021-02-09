public class CommandResult {
    private String feedbackToUser;

    public CommandResult() {}

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser(){
        return this.feedbackToUser;
    }
}
