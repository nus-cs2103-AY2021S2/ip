package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Add command.
 *
 * Adds task to task list and writes changes to file.
 */
public class DukeCommandAdd extends DukeCommand {

    private Task task;

    public DukeCommandAdd(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeExceptionFileNotWritable {
        tasks.addTask(task);
        loader.write(tasks);
        ui.showMessage(
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
