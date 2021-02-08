package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Delete command.
 *
 * Removes single or all tasks from task list, the latter executed using the 'delete all'
 * keyphrase.
 */
public class DukeCommandDelete extends DukeCommand {

    private final ArrayList<Integer> indices = new ArrayList<>();
    private boolean isDeleteAll = false;

    public DukeCommandDelete(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        if (tokenSet.contains("all")) {
            isDeleteAll = true;
        } else {
            try {
                String indices = tokenSet.get("/text");
                for (String s : indices.split("\\s+")) {
                    this.indices.add(Integer.parseInt(s) - 1);
                }
            } catch (Exception e) {
                throw new DukeExceptionIllegalArgument("Need to specify task number to delete.");
            }
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader)
            throws DukeExceptionFileNotWritable, DukeExceptionIllegalArgument {
        if (isDeleteAll) {
            String reply = ui.getUserInput("Confirm deletion of all tasks (y/[n])? ");
            if (reply.equalsIgnoreCase("y")) {
                tasks.deleteAll();
                loader.write(tasks);
                ui.showMessage("All tasks successfully deleted.");
            } else {
                ui.showMessage("No tasks deleted.");
            }

        } else {
            ArrayList<String> taskStrings = new ArrayList<>();
            for (int index : indices) {
                taskStrings.add("  " + tasks.getTask(index));
                tasks.deleteTask(index);
                tasks.setDone(index);
            }
            loader.write(tasks);
            ui.showMessage(
                    "Noted. I've removed these tasks:",
                    taskStrings,
                    "Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
