package todobeast.commands;

import todobeast.*;
import todobeast.exceptions.InvalidInputException;
import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {

    TaskType taskType;
    String taskDescription;
    LocalDate taskDate;
    LocalTime taskTime;

    public AddCommand(TaskType taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = null;
        this.taskTime = null;
    }

    public AddCommand(TaskType taskType, String taskDescription, LocalDate taskDate, LocalTime taskTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

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
