package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     *  DeleteCommand constructor.
     *
     *  @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    /**
     *  Executes DeleteCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     *  @return Command response.
     *  @throws DukeException If any error arises from execution.
     */
    public String execute(TaskManager tm, Storage st) throws DukeException {
        Task t = tm.deleteTask(taskIndex);
        st.save(tm);
        String res = "The following task has been removed: \n";
        res += t.toString() + "\n";
        res += String.format("Now you have %d task(s)",
                tm.getSize());
        return res;

    }
}
