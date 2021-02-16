package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    /**
     * Creates a command for exiting the bot
     */
    public ByeCommand() {
        super();
    }

    /**
     * Execute the action to exit bot
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     */
    public ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> returnMsg = new ArrayList<>();
        System.exit(0);
        returnMsg.add(ui.speak("Goodbye for now, we will meet again."));
        return returnMsg;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
