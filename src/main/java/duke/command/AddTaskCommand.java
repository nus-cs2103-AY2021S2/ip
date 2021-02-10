package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddTaskCommand extends Command {
    private Task task;

    /**
     * Creates a command for 'adding task'
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Execute action to add a new task to existing lists of task
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> returnMsg = new ArrayList<>();
        int currentTasksSize = tasks.size();
        tasks.add(task);
        assert(tasks.size() == currentTasksSize + 1) : "New task has not been added, count not updated";
        returnMsg.add(ui.speak(task.getAddMessage() + (task.getAddMessage() == null ? "" : " ")
                + "I've added: " + task));
        returnMsg.add(ui.speak("You now have " + tasks.size() + " tasks at hand."));
        return returnMsg;
    }
}
