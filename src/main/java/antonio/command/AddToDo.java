package antonio.command;

import antonio.TaskList;
import antonio.task.ToDoTask;
/**
 * Represents a command that adds to-do tasks.
 */
public class AddToDo extends AddCommand {

    /**
     * Constructs a commands that adds to-do tasks.
     * @param commandType Type of command.
     * @param description Description of the task.
     */
    public AddToDo(String commandType, String description) {
        super(commandType, description);
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new ToDoTask(description, taskID);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

}
