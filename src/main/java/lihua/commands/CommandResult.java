package lihua.commands;

public class CommandResult {
    private final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedBack() {
        return feedbackToUser;
    }
}
