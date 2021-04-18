package duke.command;

/**
 * A class containing the information to pass to Duke from Command class  after that Command is executed.
 *
 */
public class CommandResult {
    private String messageToDisplay;
    private boolean isExit;

    /**
     * Constructor that stores the necessary information
     * @param messageToDisplay the message for the GUI to display.
     * @param isExit the flag indicating if the program should stop after the command is run.
     */
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
