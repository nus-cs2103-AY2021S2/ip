package percy.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import percy.exception.ParsingException;
import percy.storage.Storage;
import percy.task.Task;
import percy.task.TaskList;
import percy.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<String>(List.of(
            "delete: Deletes a task from the list.",
            "Parameters: TASK_NUMBER",
            "Example: delete 2"));
    private int taskNum;

    /**
     * Constructs the delete command.
     * @param taskNum number of the task in the list.
     */
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
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task deleteTask = taskList.getTaskList().get(taskNum - 1);
            assert taskList.getTaskList().size() >= taskNum;
            taskList.deleteTaskFromList(deleteTask);
            storage.save(taskList);
            return Ui.makeDeleteMsg(deleteTask, taskList);
        } catch (IOException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            return new ParsingException(DeleteCommand.COMMAND).toString();
        }
    }
}
