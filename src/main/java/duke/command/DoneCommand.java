package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.arguments) - 1;
        Task doneTask = tasks.get(index);
        doneTask.setDone(true);
        storage.saveTasksToFile(tasks);
        String dukeResponse = "Nice! I've marked this task as done: \n"
                + doneTask.toString();
        return dukeResponse;
    }
}
