package duke.maincomponents;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * TaskList class, which stores all the Tasks in the list
 */
public class TaskList {
    private ArrayList<Task> TaskArray;
    private int numberOfTasks;

    /**
     * Default constructor for TaskList, returns a TaskList object with an empty Tasklist
     */
    public TaskList() {
        this.TaskArray = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    /**
     * Constructor for TaskList which returns a Tasklist that contains the tasks in taskArrayToLoad
     * @param taskArrayToLoad ArrayList which contains tasks to put inside TaskList
     */
    public TaskList(ArrayList<Task> taskArrayToLoad) {
        this.TaskArray = new ArrayList<>(taskArrayToLoad);
        this.numberOfTasks = TaskArray.size();
    }

    /**
     * @return Returns number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * @return Returns a arraylist containing all the current tasks in the tasklist
     */
    public ArrayList<Task> getCurrentTaskList() {
        return new ArrayList<Task>(TaskArray);
    }

    /**
     * Checks a given index's task as done
     * @param number index of task to mark as done
     * @return returns tasks that has been marked as done
     * @throws DukeException error thrown if the index given has no corresponding task
     */
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

    /**
     * Deletes a given index's task from the TaskList
     * @param taskInt index of task to delete
     * @return the delete task
     * @throws DukeException error thrown if the index given has no corresponding task
     */
    public Task deleteTask(int taskInt) throws DukeException {
        if (taskInt >= 1 && taskInt <= TaskArray.size()){

            Task todelete = TaskArray.get(taskInt - 1);
            TaskArray.remove(taskInt - 1);
            numberOfTasks = numberOfTasks - 1;

            return todelete;
        } else {
            throw new DukeException("Error! Please make sure the number given has a corresponding task!");
        }
    }

    /**
     * Add a to do task to the tasklist
     * @param description description of the to do task
     * @return returns the to do task that has been added
     */
    public Task addToDoTask(String description) {
        ToDo newToDo = new ToDo(description);
        TaskArray.add(newToDo);
        numberOfTasks = numberOfTasks + 1;

        return newToDo;
    }

    /**
     * Add a event task to the tasklist
     * @param description arraylist of description of the event task, index 0 has the description and
     *                    index 1 has the event at description
     * @return returns the event task that has been added
     */
    public Task addEventTask(ArrayList<String> description) {
        Event newEvent = new Event(description.get(0), description.get(1));
        TaskArray.add(newEvent);
        numberOfTasks = numberOfTasks + 1;

        return newEvent;
    }


    /**
     * Add a deadline task to the tasklist
     * @param description arraylist of description of the event task, index 0 has the description and
     *                    index 1 has the event by description
     * @return returns the deadline task that has been added
     * @throws DukeException
     */
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
