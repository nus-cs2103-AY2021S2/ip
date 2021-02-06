package surrealchat.command;

import surrealchat.exception.SurrealException;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.task.TaskPriority;
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

    private ToDoTask addToDo(String taskDescription) throws SurrealException {
        if (taskDescription.isEmpty()) {
            throw new SurrealException("Empty todo task description. Not stonks!\n");
        }
        try {
            String[] descriptionSplitArray = taskDescription.split(";");
            int intPriority = Integer.valueOf(descriptionSplitArray[1].trim());
            TaskPriority taskPriority = TaskPriority.getPriorityType(intPriority);
            return ToDoTask.createNewToDoTask(descriptionSplitArray[0].trim(), taskPriority);
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new SurrealException("Wrong formatting. Did you forget to put ';'? Not stonks!\n");
        } catch (NumberFormatException e) { //Happens if correct int is not passed in for priority
            throw new SurrealException("Priority argument must be integer in range 1-3! Not stonks!\n");
        }
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
        } catch (SurrealException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of todo command.
     * @return String describing the todo command.
     */
    public static String displayHelp() {
        String outputString = "Given a description, stores todo task.\n";
        outputString += "Format of arguments: todo [description] ; [priority]\n";
        return outputString;
    }
}
