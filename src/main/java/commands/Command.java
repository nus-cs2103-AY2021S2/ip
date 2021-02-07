package commands;

import exceptions.InvalidArgumentException;
import format.Ui;
import tasklist.TaskList;

public abstract class Command {

    String[] commandList =
            {"bye", "list", "event", "todo", "deadline", "delete", "done"};

    /** For description and toString purposes. Not for switch case or anything that an enum
     * could do better. Should correspond with the command to type in? */
    String commandName;

    protected String commandBody;
    protected String commandOutputMsg;
    protected boolean hasRunSuccessfully = false;
    protected boolean hasSentExitDukeSignal = false;
    // won't be needed for gui? what's the gui equivalent? why should something related to gui/cli be implemented
    // at this level

    protected Command(String commandName, String commandBody) {
        this.commandName = commandName;
        this.commandBody = commandBody;
    }

    // think all commands should have a general run method?
    /**
     * Runs a command and stores output and status in instance variables.
     * @param
     */
    public abstract void run(TaskList taskList);
    // exceptions thrown from running these commands should be caught in this run method so they can be stored in command output?

    @Override
    public String toString() {
        return "Command{" +
                "commandName='" + commandName + '\'' +
                '}';
    }

    public String getCommandOutputMsg() {
        return commandOutputMsg;
    }

    public boolean hasRunSuccessfully() {
        return hasRunSuccessfully;
    }

    public boolean hasSentExitDukeSignal() {
        return hasSentExitDukeSignal;
    }

    protected void handleInvalidOnEmptyList() {
        this.commandBody =
                new InvalidArgumentException("This command cannot be done on an empty task list.").getMessage();
    }

    protected void handleException(Exception e) {
        this.commandBody = Ui.formatException(e.getMessage());
    }
}
