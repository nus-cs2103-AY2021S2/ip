package duke.tasks;

import static duke.common.Utils.DATE_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of task.
 * Supports various operations for manipulating the task list.
 */
public class TaskList {

    private final List<Task> tasks;

    /** Constructor to instantiate an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to instantiate a pre-populated task list.
     *
     * @param tasks list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Add {@code Task} to the task list.
     *
     * @param task {@code Task} to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delete {@code Task} from the task list.
     *
     * @param index index of the task to be removed
     * @return the deleted task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns {@code Task} at the given index.
     *
     * @param index index of the task to be retrieve
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns {@code Task} that contains the search query.
     *
     * @param query the search query
     * @return task list containing the matching tasks
     */
    public TaskList find(String query) {
        List<Task> queryTasks = tasks.stream().filter(task ->
             task.getDescription().contains(query)
        ).collect(Collectors.toList());
        return new TaskList(queryTasks);
    }

    /**
     * Returns the size of the task list
     *
     * @return size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Clones the current task list.
     *
     * @return copy of the current task list
     */
    public TaskList clone() {
        return new TaskList(tasks);
    }

    /**
     * Deletes all the {@code Task} in the task list.
     */
    public TaskList clear() {
        TaskList taskList = clone();
        tasks.clear();
        return taskList;
    }

    /**
     * Sets all the tasks in the task list to done.
     */
    public void setAllDone() {
        tasks.forEach(Task::setDone);
    }

    /**
     * Checks whether are all the tasks in the task lists done.
     *
     * @return boolean to indicate are all the tasks done
     */
    public boolean isAllDone() {
        return tasks.stream().allMatch(Task::getDone);
    }

    /**
     * Filters the task list and returns the {@code Task} occurring on the specified date.
     *
     * @param date query date
     * @return task list containing {@code Task} that is occurring on the specified date
     */
    public TaskList filterByDate(String date) {
        LocalDate queryDate = LocalDate.parse(date, DATE_FORMAT);
        List<Task> printTasks = tasks.stream().filter(task -> {
            if (task instanceof TimedTask) {
                return ((TimedTask) task).getTaskDate().isEqual(queryDate);
            }

            return false;
        }).collect(Collectors.toList());
        return new TaskList(printTasks);
    }

    /**
     * Returns true if there is a timing clash with the input date.
     *
     * @param addedDate date to be checked
     * @return true if there is a timing clash
     */
    public boolean checkForAnomalies(LocalDateTime addedDate) {
        return tasks.stream().anyMatch(task -> {
            if (task instanceof TimedTask) {
                return ((TimedTask) task).getTaskDateTime().equals(addedDate);
            }

            return false;
        });
    }

    /**
     * Returns true if the task exists in the task list.
     *
     * @param taskToBeChecked task to be checked
     * @return true if the task exists in the task list
     */
    public boolean checkForDuplicates(Task taskToBeChecked) {
        return tasks.stream().anyMatch(task -> task.equals(taskToBeChecked));
    }

    /**
     * Formats all the {@code Task}'s information into a formatted string that is suitable for storing.
     *
     * @return formatted string that is suitable for storing
     */
    public String toStorageString() {
        return tasks.stream().map(Task::toStorageString).collect(Collectors.joining("\n"));
    }
}
