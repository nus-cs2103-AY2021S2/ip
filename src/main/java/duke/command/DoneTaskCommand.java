package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneTaskCommand extends Command {

    private int index;

    /**
     * Creates a command for 'done task'
     * @param index index of task to be deleted
     */
    public DoneTaskCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * Execute action to mark a task as done from existing lists of task
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> returnMsg = new ArrayList<>();
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number does not work, try again?");
        }
        Task doneTask = tasks.find(index);
        doneTask.setDone();
        returnMsg.add(ui.speak("Swee! This task is done: " + doneTask));
        storage.writeToFile(tasks);
        return returnMsg;
    }
}
