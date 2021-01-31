package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskIndex;

    /**
     *  DoneCommand constructor.
     *
     *  @param index Index of task to be marked as completed.
     */
    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    /**
     *  Executes DoneCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     *  @return Command response.
     *  @throws DukeException If any error arises from execution.
     */
    public String execute(TaskManager tm, Storage st) throws DukeException {
        Task t = tm.markTaskAsDone(taskIndex);
        st.save(tm);
        String res = "Marked as Done: \n";
        res += t.toString();
        return res;
    }
}
