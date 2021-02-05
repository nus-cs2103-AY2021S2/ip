package duke.controller;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A class to hold list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Creates an empty list of Tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a list of Tasks based on the collection of Tasks inputted.
     *
     * @param listOfTasks List of Tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        assert listOfTasks != null;
        this.listOfTasks = listOfTasks;
    }

    /**
     * Adds a Task into the current list.
     *
     * @param t New Task.
     */
    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    /**
     * Removes Task from the specified index (indexed from 1).
     *
     * @param number Index of the Task to be removed.
     * @return The removed Task.
     */
    public Task deleteTask(int number) {
        return this.listOfTasks.remove(number - 1);
    }

    /**
     * Marks a Task as done based on the specified index (indexed from 1).
     *
     * @param number Index of the Task to be marked.
     */
    public void markAsDone(int number) {
        this.listOfTasks.get(number - 1).setDone(true);
    }

    /**
     * Obtains the Task from the specified index (indexed from 1).
     *
     * @param number Index of the Task to be obtained.
     * @return The Task at the index.
     */
    public Task getTaskAtIndex(int number) {
        return this.listOfTasks.get(number - 1);
    }

    /**
     * Returns the number of tasks in the current list.
     *
     * @return The number of tasks in the current list.
     */
    public int getNumberOfTasks(){
        return this.listOfTasks.size();
    }

    /**
     * Returns a string representation of the current list of Tasks.
     *
     * @return A string representation of the current list of Tasks.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            result.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Find the Tasks which names contain the keyword.
     * @param keyword Keyword to be searched against.
     * @return New TaskList containing filtered results.
     */
    public TaskList find(String keyword) {
        return new TaskList(this.listOfTasks.stream()
                .filter((task) -> task.getTaskName().
                        toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Remind users the undone Tasks in the next few days as requested by the user.
     * @param days Number of days
     * @return The undone tasks within the next <code>days</code> days after today.
     */
    public TaskList remind(int days) {
        return new TaskList(this.listOfTasks.stream()
                .filter(task -> task instanceof Event || task instanceof Deadline)
                .filter(task -> // filter overdue tasks
                        LocalDate.now().compareTo(task.getTaskTime()) < 0)
                .filter(task -> // filter upcoming tasks
                        LocalDate.now().plusDays(days).compareTo(task.getTaskTime()) > 0)
                .filter(task -> !task.isDone())
                .collect(Collectors.toCollection(ArrayList::new)));

    }
}
