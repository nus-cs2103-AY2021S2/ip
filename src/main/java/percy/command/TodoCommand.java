package percy.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import percy.exception.ParsingException;
import percy.storage.Storage;
import percy.task.Task;
import percy.task.TaskList;
import percy.task.Todo;
import percy.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<>(List.of(
            "todo: Adds a generic task with a description",
            "Parameters: TASK_DESCRIPTION",
            "Example: todo feed dog"));


    private final String todoDescription;

    /**
     * Constructs todo command.
     * @param todoDescription description of todo.
     */
    public TodoCommand(String todoDescription) {
        super(false);
        this.todoDescription = todoDescription;
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
    public String execute(TaskList taskList, Storage storage) throws IOException {
        try {
            if (todoDescription.isEmpty()) {
                throw new ParsingException("todo");
            }
        } catch (ParsingException ex) {
            return Ui.makeMsg(ex.toString());
        }
        Task todo = new Todo(todoDescription);
        taskList.addTaskToList(todo);
        storage.save(taskList);
        return Ui.makeAddMsg(todo, taskList);
    }
}
