package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Delete command.
 *
 * Removes single or all tasks from task list, the latter executed using the 'delete all'
 * keyphrase.
 */
public class DukeCommandDelete extends DukeCommand {

    private final ArrayList<Integer> indices = new ArrayList<>();
    private boolean isDeleteAll = false;

    /**
     * Constructor to track task indices to delete.
     *
     * @param tokenSet user input tokens
     * @throws DukeExceptionIllegalArgument When indices cannot be parsed as integers
     */
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

    /**
     * Deletes tasks from tasklist, writes to file and displays success
     *
     * @param tasks tasklist
     * @param ui user interface
     * @param loader storage
     * @throws DukeExceptionFileNotWritable when loader fails to write to file
     * @throws DukeExceptionIllegalArgument when task fails to be parsed
     */
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
