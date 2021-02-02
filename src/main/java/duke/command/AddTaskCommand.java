package duke.command;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.speak(task.getAddMessage() + (task.getAddMessage() == null ? "" : " ") + "I've added:");
        System.out.println(task);
        ui.speak("You now have " + tasks.size() + " tasks at hand.");
    }
}
