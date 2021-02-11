package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class AddTaskCommand extends Command {
    protected String description;

    public AddTaskCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        storage.saveTasksToFile(tasks);
        String dukeResponse = "Got it I've added this task: \n"
                + tasks.get(tasks.size() - 1).toString()
                + "Now you have " + tasks.size() + " in the list\n";
        return dukeResponse;
    }
}
