package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.arguments);
        Task deletedTask = tasks.remove(index);
        storage.saveTasksToFile(tasks);
        String dukeResponse = "Noted I've removed this task: \n"
                + deletedTask.toString()
                + "You now have " + tasks.size() + " tasks in the list";
        return dukeResponse;
    }
}
