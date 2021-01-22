package commands;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import exceptions.DukeException;
import tasks.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        Task t = tm.deleteTask(taskIndex);
        st.save(tm);

        ui.println("    The following task has been removed: ");
        ui.println("       " + t.toString());
        ui.println(String.format("    Now you have %d task(s)",
                tm.getSize()));
    }
}
