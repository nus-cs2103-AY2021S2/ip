package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class PriorityCommand extends Command {
    PriorityCommand(String indexAndPrior) {
        super(null, indexAndPrior, null, null, false);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] args = description.split(" ");
        String index = args[0].trim();
        String priority = args[1].trim();

        Task t = tasks.setPriority(index, priority);
        storage.save(tasks.listOutTaskInString());
        return ui.showPrior(t.toString(), priority);
    }
}
