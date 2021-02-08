package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Delete command.
 *
 * Removes single or all tasks from task list, the latter executed using the 'delete all'
 * keyphrase.
 */
public class DukeCommandDelete extends DukeCommand {

    private final int index;

    public DukeCommandDelete(String arg) throws DukeExceptionIllegalArgument {
        if (arg.equals("all")) {
            this.index = -1; // Special delete all
        } else {
            try {
                this.index = Integer.parseInt(arg) - 1;
            } catch (Exception e) {
                throw new DukeExceptionIllegalArgument("Need to specify task number to delete.");
            }
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader)
            throws DukeExceptionFileNotWritable, DukeExceptionIllegalArgument {
        if (index == -1) {
            String reply = ui.getUserInput("Confirm deletion of all tasks (y/[n])? ");
            if (reply.equalsIgnoreCase("y")) {
                tasks.deleteAll();
                loader.write(tasks);
                ui.showMessage("All tasks successfully deleted.");
            } else {
                ui.showMessage("No tasks deleted.");
            }

        } else {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            loader.write(tasks);
            ui.showMessage(
                    "Noted. I've removed this task:",
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
