package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {

    private final int index;
    private final String newDescription;

    private static final String EMPTY_DESCRIPTION_ERROR = "No blank descriptions allowed!";

    public UpdateCommand(String argument) throws DukeException {
        try {
            if(argument.equals("")) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR);
            }
            String[] argumentArray = argument.split(" ", 2);
            index = Integer.parseInt(argumentArray[0]);
            newDescription = argumentArray[1];
        } catch (DukeException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task targetTask = tasks.editTask(index, newDescription);
        storage.write(tasks);
        return ui.getUpdatedTaskMsg(targetTask, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
