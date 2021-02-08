package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Done command.
 *
 * Marks task as done and saves task list to file.
 */
public class DukeCommandDone extends DukeCommand {

    private final int index;

    public DukeCommandDone(String arg) throws DukeExceptionIllegalArgument {
        try {
            this.index = Integer.parseInt(arg) - 1;
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Need to specify task number to mark as done.");
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
