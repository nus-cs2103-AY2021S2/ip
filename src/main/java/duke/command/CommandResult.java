package duke.command;

public class CommandResult {
    private String messageToDisplay;
    private boolean isExit;

    CommandResult(String messageToDisplay, boolean isExit) {
        this.messageToDisplay = messageToDisplay;
        this.isExit = isExit;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }
    public boolean getIsExit() {
        return isExit;
    }
}
