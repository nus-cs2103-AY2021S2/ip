package duke.maincomponents;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


public class TaskList {
    private ArrayList<Task> TaskArray;
    private int numberOfTasks;

    public TaskList() {
        this.TaskArray = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    public TaskList(ArrayList<Task> taskArrayToLoad) {
        this.TaskArray = new ArrayList<>(taskArrayToLoad);
        this.numberOfTasks = TaskArray.size();
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public ArrayList<Task> getCurrentTaskList() {
        return new ArrayList<Task>(TaskArray);
    }

    public Task checkTaskAsDone(int number) throws DukeException {
        if (number >= 1 && number <= TaskArray.size()) {
            Task currentTask = TaskArray.get(number - 1);
            currentTask.changeTaskToDone();

            return currentTask;
        } else {
            throw new DukeException("Error! Please make sure the"
                    + " number given has a corresponding task!");
        }
    }

    public Task deleteTask(int taskInt) throws DukeException {
        if (taskInt >= 1 && taskInt <= TaskArray.size()) {

            Task todelete = TaskArray.get(taskInt - 1);
            TaskArray.remove(taskInt - 1);
            numberOfTasks = numberOfTasks - 1;

            return todelete;
        } else {
            throw new DukeException("Error! Please make sure the number given has a corresponding task!");
        }
    }

    public Task addToDoTask(String description) {
        ToDo newToDo = new ToDo(description);
        TaskArray.add(newToDo);
        numberOfTasks = numberOfTasks + 1;

        return newToDo;
    }

    public Task addEventTask(ArrayList<String> description) {
        Event newEvent = new Event(description.get(0), description.get(1));
        TaskArray.add(newEvent);
        numberOfTasks = numberOfTasks + 1;

        return newEvent;
    }

    public Task addDeadlineTask(ArrayList<String> description) throws DukeException {
        try {
            Deadline newDeadline = new Deadline(description.get(0), description.get(1));
            TaskArray.add(newDeadline);
            numberOfTasks = numberOfTasks + 1;
            return newDeadline;
        } catch (TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
