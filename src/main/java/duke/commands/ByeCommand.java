package duke.commands;

import java.io.IOException;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    /**
     * Saves all Tasks in taskList to local file, and then prints exit message.
     * @return exit message to be displayed.
     */
    @Override
    public String execute() {
        try {
            this.storage.writeToFile(this.taskList);

            return "Bye. Hope to see you again soon!";
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Returns signal indicating to exit the program.
     * @return boolean signal indicating to exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
