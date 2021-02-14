package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Todo;

public class ToDoCommand extends Command {
    private TaskList taskList;
    private String taskDescription;

    public ToDoCommand(TaskList taskList, String taskDescription) {
        super(taskList);
        this.taskDescription = taskDescription;
    }

    public TaskList execute() {
        TaskList taskList = this.getTaskList();
        Todo todo = new Todo(this.taskDescription);
        taskList = taskList.add(todo);
        return taskList;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Todo todo = new Todo(this.taskDescription);
        message += todo.toString() + "\n";
        message += "Now you have " + this.getTaskList().size() + " tasks in the list.";
        return message;
    }
}