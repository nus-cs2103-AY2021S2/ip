package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private String index;
    public DoneCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = ui.getMarkMessage(tasks.markDone(Integer.parseInt(index)));
            storage.save(tasks);
            return message;
        } catch (NumberFormatException ex) {
            return "Please enter integer values..";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
