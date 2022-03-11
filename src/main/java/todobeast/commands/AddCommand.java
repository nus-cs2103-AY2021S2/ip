package todobeast.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.InvalidInputException;
import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

/**
 * A Command that represents adding a new Task to the TaskList. Enclosing business logic is wrapped in the execute()
 * method.
 */
public class AddCommand extends Command {

    private final TaskType taskType;
    private final String taskDescription;
    private final LocalDate taskDate;
    private final LocalTime taskTime;
    private final String taskNotes;

    /**
     * Constructor for tasks.
     * @param taskType enumeration representing the type of task to be created
     * @param taskDescription the description corresponding to the current task
     * @param taskDate the date corresponding to the current task
     * @param taskTime the time corresponding to the current task
     */
    public AddCommand(TaskType taskType, String taskDescription, LocalDate taskDate, LocalTime taskTime,
                      String taskNotes) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskNotes = taskNotes;
    }

    /**
     * Handles the creation of a new task within the application.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws InvalidInputException if the command provided by the user is invalid
     */
    public void execute(TaskList taskList, Ui ui) throws InvalidInputException {

        Task newTask = null;
        boolean hasTaskNotes = taskNotes != null;
        boolean hasDate = taskDate != null;
        boolean hasTime = taskTime != null;
        switch (taskType) {
        case TODO:
            if (hasTaskNotes) {
                newTask = new Todo(taskDescription, false, taskNotes);
            } else {
                newTask = new Todo(taskDescription, false, null);
            }
            break;
        case DEADLINE:
            if (hasDate && hasTime) {
                if (hasTaskNotes) {
                    newTask = new Deadline(taskDescription, false, taskDate, taskTime, taskNotes);
                } else {
                    newTask = new Deadline(taskDescription, false, taskDate, taskTime, null);
                }
            } else {
                throw new InvalidInputException("Task time and/or date not provided.");
            }
            break;
        case EVENT:
            if (hasDate && hasTime) {
                if (hasTaskNotes) {
                    newTask = new Event(taskDescription, false, taskDate, taskTime, taskNotes);
                } else {
                    newTask = new Event(taskDescription, false, taskDate, taskTime, null);
                }
            } else {
                throw new InvalidInputException("Task time and/or date not provided.");
            }
            break;
        default:
            throw new InvalidInputException("Task type provided is invalid!");
        }
        taskList.addTask(newTask);
        ui.addToResponseOutput(ui.showAdded(newTask));
        ui.addToResponseOutput(ui.showNumOfTasks(taskList.getNumOfTasks()));
    }
}
