package todobeast.commands;

import todobeast.*;
import todobeast.exceptions.InvalidInputException;
import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A Command that represents adding a new Task to the TaskList. Enclosing business logic is wrapped in the execute()
 * method.
 */
public class AddCommand extends Command {

    TaskType taskType;
    String taskDescription;
    LocalDate taskDate;
    LocalTime taskTime;

    /**
     * Constructor for tasks that do not have a date and time requirement (e.g. todo-type tasks)
     * @param taskType enumeration representing the type of task to be created
     * @param taskDescription the description corresponding to the current task
     */
    public AddCommand(TaskType taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = null;
        this.taskTime = null;
    }

    /**
     * Constructor for tasks that have a date and time requirement (e.g. deadline and event-type tasks)
     * @param taskType enumeration representing the type of task to be created
     * @param taskDescription the description corresponding to the current task
     * @param taskDate the date corresponding to the current task
     * @param taskTime the time corresponding to the current task
     */
    public AddCommand(TaskType taskType, String taskDescription, LocalDate taskDate, LocalTime taskTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    /**
     * Handles the creation of a new task within the application.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws InvalidInputException if the command provided by the user is invalid
     */
    public void execute(TaskList taskList, Ui ui) throws InvalidInputException {

        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new Todo(taskDescription);
            break;
        case DEADLINE:
            if (taskDate == null || taskTime == null) {
                throw new InvalidInputException("Task time and/or date not provided.");
            }
            newTask = new Deadline(taskDescription, taskDate, taskTime);
            break;
        case EVENT:
            if (taskDate == null || taskTime == null) {
                throw new InvalidInputException("Task time and/or date not provided.");
            }
            newTask = new Event(taskDescription, taskDate, taskTime);
            break;
        }
        taskList.addTask(newTask);
        ui.showAdded(newTask);
        ui.showNumOfTasks(taskList.getNumOfTasks());
    }
}
