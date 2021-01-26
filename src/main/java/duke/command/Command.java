package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Handles the commands input from the user
 */
public class Command {

    protected String reply;
    protected boolean isBye = false;

    /**
     * Constructor to initialise the reply of the command
     * @param reply the reply of the command
     */
    public Command(String reply){
        this.reply = reply;
    }

    /**
     * Checks if the command is "bye", in which case terminate Duke
     * @return whether the command is a ByeCommand
     */
    public boolean getIsBye(){
        return this.isBye;
    }

    /**
     * Executes the command
     * @param ui the ui to respond to the user's input
     * @param s The storage to save the tasklist to
     * @param list The current list of tasks
     * @throws IOException when the loading fails
     */
    public void execute(Ui ui, Storage s, TaskList list)throws IOException {
    }
}
