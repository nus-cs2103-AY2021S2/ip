package duke.commands;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DukeCommandList extends DukeCommand {
    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeExceptionIllegalArgument {
        // Output
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add((i+1) + "." + tasks.getTask(i));
        }
        ui.showMessage("Here are the tasks in your list:", lines);
    }
}
