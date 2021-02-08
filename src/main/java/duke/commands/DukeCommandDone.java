package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Done command.
 *
 * Marks task as done and saves task list to file.
 */
public class DukeCommandDone extends DukeCommand {

    private final ArrayList<Integer> indices = new ArrayList<>();

    public DukeCommandDone(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        try {
            String indices = tokenSet.get("/text");
            for (String s : indices.split("\\s+")) {
                this.indices.add(Integer.parseInt(s) - 1);
            }
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Need to specify task number to mark as done.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader)
            throws DukeExceptionFileNotWritable, DukeExceptionIllegalArgument {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (int index : indices) {
            tasks.setDone(index);
            taskStrings.add("  " + tasks.getTask(index));
        }
        loader.write(tasks);
        ui.showMessage("Nice! I've marked these tasks as done:", taskStrings);
    }
}
