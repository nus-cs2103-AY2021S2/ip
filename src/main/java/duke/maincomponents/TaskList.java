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
    private ArrayList<Task> tasks;
    private int numberOfTasks;

    /**
     * Default constructor for TaskList, returns a TaskList object with an empty Tasklist
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    /**
     * Alternative Constructor for TaskList which returns a Tasklist that contains the tasks in taskArrayToLoad
     *
     * @param taskArrayToLoad ArrayList which contains tasks to put inside TaskList
     */
    public TaskList(ArrayList<Task> taskArrayToLoad) {
        this.tasks = new ArrayList<>(taskArrayToLoad);
        this.numberOfTasks = tasks.size();
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
        return new ArrayList<Task>(tasks);
    }

    /**
     * Marks a given index's task as done
     *
     * @param number Index of task to mark as done
     * @return Returns tasks that has been marked as done
     * @throws DukeException Error thrown if the index given has no corresponding task
     */
    public Task checkTaskAsDone(int number) throws DukeException {
        if (number >= 1 && number <= tasks.size()) {
            Task currentTask = tasks.get(number - 1);
            currentTask.changeTaskToDone();

            return currentTask;
        } else {
            throw new DukeException("Error! Please make sure the"
                    + " number given has a corresponding task!");
        }
    }

    /**
     * Deletes a given index's task from the TaskList
     *
     * @param taskInt Index of task to delete
     * @return Deleted task
     * @throws DukeException Error thrown if the index given has no corresponding task
     */
    public Task deleteTask(int taskInt) throws DukeException {
        if (taskInt >= 1 && taskInt <= tasks.size()) {
            Task todelete = tasks.get(taskInt - 1);
            tasks.remove(taskInt - 1);
            numberOfTasks = numberOfTasks - 1;

            return todelete;
        } else {
            throw new DukeException("Error! Please make sure the number given has a corresponding task!");
        }
    }

    /**
     * Adds a to do task to the Task List
     *
     * @param description Description of the to do task
     * @return Returns the to do task that has been added
     */
    public Task addToDoTask(String description) {
        ToDo newToDo = new ToDo(description);
        tasks.add(newToDo);
        numberOfTasks = numberOfTasks + 1;

        return newToDo;
    }

    /**
     * Adds a event task to the tasklist
     *
     * @param description Array List of description of the event task, index 0 has the description and
     *                    index 1 has the event at description
     * @return Returns the event task that has been added
     */
    public Task addEventTask(ArrayList<String> description) {
        Event newEvent = new Event(description.get(0), description.get(1));
        tasks.add(newEvent);
        numberOfTasks = numberOfTasks + 1;

        return newEvent;
    }


    /**
     * Adds a deadline task to the tasklist
     *
     * @param description Array List of description of the event task, index 0 has the description and
     *                    index 1 has the event by description
     * @return Returns the deadline task that has been added
     * @throws DukeException Throws an exception if an error occured when creating the deadline task
     */
    public Task addDeadlineTask(ArrayList<String> description) throws DukeException {
        try {
            Deadline newDeadline = new Deadline(description.get(0), description.get(1));
            tasks.add(newDeadline);
            numberOfTasks = numberOfTasks + 1;
            return newDeadline;
        } catch (TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns a task of a specfic index
     *
     * @param taskInt Index of the task to return
     * @return A task object
     * @throws DukeException Throws an exception if an error occured when creating the task
     */
    public Task getTask(int taskInt) throws DukeException {
        if (taskInt >= 1 && taskInt <= tasks.size()) {
            Task task = tasks.get(taskInt - 1);
            return task;
        } else {
            throw new DukeException("Error! Please make sure the number given has a corresponding task!");
        }
    }
}
