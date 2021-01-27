package percy.command;

import java.util.ArrayList;

import percy.ui.UserInterface;
import percy.task.Task;
import percy.task.TaskList;
import percy.task.Todo;

public class DoneCommand extends Command {
    public static final String COMMAND = "done";

    private int taskNum;

    public DoneCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
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
        ArrayList<Task> list = taskList.getTaskList();

        Task doneTask = list.get(taskNum - 1);
        doneTask.doTask();
        // Task doneTask = list.get(taskNum - 1).doTask();
        // list.set(taskNum - 1, doneTask);
        return UserInterface.makeDoneMsg(doneTask);
    }
}
