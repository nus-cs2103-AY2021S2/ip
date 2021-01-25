package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DukeCommandDelete extends DukeCommand {

    private int index;

    public DukeCommandDelete(int index) {
        this.index = index;
    }
    public DukeCommandDelete(String arg) throws DukeExceptionIllegalArgument {
        try {
            this.index = Integer.parseInt(arg) - 1;
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Need to specify task number to delete.");
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader)
            throws DukeExceptionFileNotWritable, DukeExceptionIllegalArgument{
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        loader.write(tasks);
        ui.showMessage(
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
