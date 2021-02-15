package duke.commands;

import duke.parser.DuplicateException;
import duke.parser.InsufficientArgumentsException;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class ToDoCommand extends Command {
    private String taskDescription;

    public ToDoCommand(TaskList taskList, String taskDescription) {
        super(taskList);
        this.taskDescription = taskDescription;
    }
    public ToDoCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = "";
    }

    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of a todo cannot be empty.");
            }
            String taskDescription = this.getDescription(this.getUserInput(), "\n");
            this.getTaskList().hasDuplicate(taskDescription);
            return new ToDoCommand(this.getTaskList(), taskDescription);
        } catch (InsufficientArgumentsException | DuplicateException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    public TaskList execute() {
        TaskList taskList = this.getTaskList();
        int initialSize = taskList.size();
        Todo todo = new Todo(this.taskDescription);
        taskList = taskList.add(todo);
        assert(initialSize + 1 == taskList.size()); // ensure that todo is properly added
        return taskList;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Todo todo = new Todo(this.taskDescription);
        message += todo.toString() + "\n";
        message += "Now you have " + (this.getTaskList().size() + 1) + " tasks in the list.";
        return message;
    }
}