package duke.command;

import duke.TaskList;
import duke.task.ToDoTask;

public class AddToDo extends AddCommand {

    /**
     * Constructor for commands.AddCommand class duke.command name and description.
     *
     * @param commandType Type of the command
     * @param description Description of the duke.command.
     */
    public AddToDo(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new ToDoTask(description, taskID);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }
}
