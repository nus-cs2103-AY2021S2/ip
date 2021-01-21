package percy.command;

import percy.ui.UserInterface;
import percy.task.Deadline;
import percy.task.Task;
import percy.task.TaskList;

public class DeadlineCommand extends Command {
    private String deadlineDescription;
    private String date;

    public DeadlineCommand(String deadlineDescription, String date) {
        super(false);
        this.deadlineDescription = deadlineDescription;
        this.date = date;
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList) { // Is task list Immutable?
        Task deadlineTask = new Deadline(deadlineDescription, date);

        taskList.addTaskToList(deadlineTask);
        return UserInterface.makeAddMsg(deadlineTask, taskList);
    }
}
