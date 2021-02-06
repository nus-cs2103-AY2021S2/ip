package commands; 

public abstract class Command {

    String[] commandList =
            {"bye", "list", "event", "todo", "deadline", "delete", "done"};

    /** For description and toString purposes. Not for switch case or anything that an enum
     * could do better. Should correspond with the command to type in? */
    String commandName;

    protected String commandBody;
    protected String commandOutput;
    protected boolean hasRunSuccessfully;
    protected boolean hasSentExitDukeSignal;
    // won't be needed for gui? what's the gui equivalent? why should something related to gui/cli be implemented
    // at this level

    protected Command(String commandName, String commandBody) {
        this.commandName = commandName;
        this.commandBody = commandBody;
    }

    /**
     * Runs a command and stores output and status in instance variables.
     * @param
     */
    public abstract void run(); // todo TaskList taskList

    @Override
    public String toString() {
        return "Command{" +
                "commandName='" + commandName + '\'' +
                '}';
    }

    public String getCommandOutput() {
        return commandOutput;
    }

    public boolean hasRunSuccessfully() {
        return hasRunSuccessfully;
    }
}
