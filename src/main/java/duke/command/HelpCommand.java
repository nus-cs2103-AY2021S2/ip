package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class HelpCommand extends Command {

    public static final boolean IS_EXIT = false;

    /**
     * Constructor method
     */
    public HelpCommand() {
        super(IS_EXIT);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpReply();
    }

}
