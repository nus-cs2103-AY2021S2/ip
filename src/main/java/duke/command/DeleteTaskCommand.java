package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Creates a command for 'deleting task'
     * @param index index of task to be deleted
     */
    public DeleteTaskCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * Execute action to delete a task from existing lists of task
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
        Task deleteTask = tasks.find(index);
        tasks.delete(deleteTask);
        returnMsg.add(ui.speak("Aww yes! I've removed this task: " + deleteTask));
        returnMsg.add(ui.speak("Now you have " + tasks.size() + " tasks left."));
        storage.writeToFile(tasks);
        return returnMsg;
    }
}
