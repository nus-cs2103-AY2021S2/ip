package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DukeCommandDone extends DukeCommand {

    private int index;

    public DukeCommandDone(int index) {
        this.index = index;
    }
    public DukeCommandDone(String arg) throws DukeExceptionIllegalArgument {
        try {
            this.index = Integer.parseInt(arg) - 1;
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Argument must be an integer.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader)
            throws DukeExceptionFileNotWritable, DukeExceptionIllegalArgument {
        tasks.setDone(index);
        loader.write(tasks);
        ui.showMessage("Nice! I've marked this task as done:", "  "+tasks.getTask(index));
    }
}
