package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    private static final String byeSuccess = "Goodbye.";
    private static final Boolean toExit = true;

    /**
     * Exits Gui
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResponse(ByeCommand.byeSuccess, ByeCommand.toExit);
    }
}
