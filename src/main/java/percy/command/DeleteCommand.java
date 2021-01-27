package percy.command;

import percy.task.Task;
import percy.task.TaskList;
import percy.ui.UserInterface;
import percy.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private int taskNum;

    public DeleteCommand(int taskNum) {
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
    public String execute(TaskList taskList, Storage storage) throws IOException { // Is task list Immutable?
        Task deleteTask = taskList.getTaskList().get(taskNum - 1);
        taskList.deleteTaskFromList(deleteTask);
        storage.save(taskList);
        return UserInterface.makeDeleteMsg(deleteTask, taskList);
    }
}
