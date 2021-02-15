package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String index;
    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = ui.getRemoveMessage(tasks, tasks.removeTask(Integer.parseInt(index)));
            storage.save(tasks);
            return message;
        } catch (NumberFormatException ex) {
            return "Please enter integer values..";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
