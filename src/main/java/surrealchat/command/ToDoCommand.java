package surrealchat.command;

import java.util.NoSuchElementException;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.task.ToDoTask;

/**
 * Command object for creating a new ToDoTask object.
 */
public class ToDoCommand extends Command {
    protected final String taskDescription;

    /**
     * Creates new ToDoCommand object.
     * @param taskDescription The description for new ToDoTask object.
     */
    public ToDoCommand(String taskDescription) {
        super("todo");
        this.taskDescription = taskDescription;
    }

    private ToDoTask addToDo(String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!\n");
        }

        return ToDoTask.createNewToDoTask(taskDescription.trim());
    }

    private String printOutput(Task task, int size) {
        String outputString = String.format("Meme Man has added todo task: %s\n", task);
        outputString += String.format("Total number of tasks: %d\n", size);
        return outputString;
    }

    /**
     * Executes todo command to generate new ToDoTask object.
     * @param taskManagement TaskManagement object to which ToDoTask is added.
     * @return String to be printed upon successful addition of DeadlineTask.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            ToDoTask addedTask = addToDo(taskDescription);
            taskManagement.addTask(addedTask);
            return printOutput(addedTask, taskManagement.getNumberOfTasks());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of todo command.
     * @return String describing the todo command.
     */
    public static String displayHelp() {
        String outputString = "Given a description, stores todo task.\n";
        outputString += "Format of arguments: todo [description]\n";
        return outputString;
    }
}
