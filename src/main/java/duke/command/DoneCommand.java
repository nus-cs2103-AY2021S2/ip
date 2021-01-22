package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        Task t = tm.markTaskAsDone(taskIndex);
        st.save(tm);

        ui.println("    Marked as Done: ");
        ui.println("      " + t.toString());
    }
}
