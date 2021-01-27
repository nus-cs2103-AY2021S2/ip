package duke.commands;

public class CommandResult {
    protected String feedback;
    protected boolean isExit;

    public CommandResult(String feedback) {
        this(feedback, false);
    }

    public CommandResult(String feedback, boolean isExit) {
        this.feedback = feedback;
        this.isExit = isExit;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean hasFeedback() {
        return feedback != null;
    }

    public boolean isExit() {
        return isExit;
    }
}
