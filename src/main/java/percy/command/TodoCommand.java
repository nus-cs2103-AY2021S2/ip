package percy.command;

import java.io.IOException;

import java.util.ArrayList;

import percy.exception.TodoException;
import percy.task.Task;
import percy.task.TaskList;
import percy.task.Todo;
import percy.ui.UserInterface;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";

    private String todoDescription;
    private String date;

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
    public String execute(TaskList taskList) { // Is task list Immutable?
        try {
            if (todoDescription.isEmpty()) {
                throw new TodoException();
            }
        } catch(TodoException ex){
            return UserInterface.makeMsg(ex.toString());
        }
        Task todo = new Todo(todoDescription);
        taskList.addTaskToList(todo);
        return UserInterface.makeAddMsg(todo, taskList);
    }
}
