package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ByeCommand extends Command {

    /**
     * Creates a command for exiting the bot
     */
    public ByeCommand() {
        super();
    }

    /**
     * Execute the action to exit bot
     * @param tasks: list of tasks
     * @param ui: UI required for conversation
     * @param storage: Storage required for .txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.speak("Goodbye for now, we will meet again.");
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
