package commands;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import tasks.DukeTask;
import ui.Ui;

import java.util.Optional;

public class ListCommand extends Command {

    private final String[] commands;
    public ListCommand(String[] commands) {
        this.commands = commands;
    }
    /**
     * Executes the List Command by presenting the displaying the DukeTasks in the TaskList.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (commands.length == 1) {
            return ui.list(tasklist, Optional.empty());
        } else {
            String listDetail = commands[1];

            switch(listDetail) {
            case ("todos"):
                return ui.list(tasklist, Optional.of(DukeTask.TaskType.TODO));
            case("deadline"):
                return ui.list(tasklist, Optional.of(DukeTask.TaskType.DEADLINE));
            case("events"):
                return ui.list(tasklist, Optional.of(DukeTask.TaskType.EVENT));
            default:
                throw new DukeException("TypeNotFound");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
