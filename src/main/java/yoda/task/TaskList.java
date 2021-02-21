package yoda.task;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Specialized list class to handle task-related instructions.
 */
public class TaskList implements Serializable {
    /** List of tasks created by user */
    private List<Task> tasks;

    /**
     * Creates a TaskList object.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks contained within the TaskList.
     * @return List of tasks in the TaskList.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Accesses a task from the list.
     * @param taskNumber Index of task in list.
     * @return Task requested by user.
     * @throws InvalidTaskListIndexException If the index of task requested is invalid.
     */
    public Task accessTask(int taskNumber) throws InvalidTaskListIndexException {
        try {
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskListIndexException("Exist in the task list, "
                    + "one of the task numbers does not!");
        }
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks tasks in the list as done based on the indexes given.
     * @param taskNumber Index(es) of tasks in the list.
     */
    public void markTaskAsDone(int ... taskNumber) {
        for (int j : taskNumber) {
            tasks.get(j - 1).markAsDone();
        }
    }

    /**
     * Marks all the tasks in the list as done.
     */
    public void markAllTasksAsDone() {
        for (Task t : tasks) {
            t.markAsDone();
        }
    }

    /**
     * Marks tasks from the list for deletion based on the indexes given.
     * @param taskNumber Index(es) of task in list.
     */
    public void markTaskToBeDeleted(int ... taskNumber) {
        for (int j : taskNumber) {
            tasks.get(j - 1).markToBeDeleted();
        }
    }

    /**
     * Deletes all tasks in the list which have been marked for deletion.
     */
    public void deleteMarkedTasks() {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).isMarkedToBeDeleted) {
                tasks.remove(i);
            }
        }
    }

    /**
     * Deletes all tasks in the list.
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Gets the number of unfinished tasks left in the list.
     * @return Number of unfinished tasks in the list.
     */
    public int getNumberOfUnfinishedTasks() {
        int unfinishedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isDone) {
                unfinishedTasks++;
            }
        }
        return unfinishedTasks;
    }

    /**
     * Filters the tasks in the list by the type or keyword requested.
     * @param type Type of task requested.
     * @return TaskList containing tasks of the type requested or tasks with the keyword requested.
     */
    public TaskList filterByTask(String type) {
        if (type.equals("ToDo")) {
            return new TaskList(tasks
                    .stream()
                    .filter(p -> p instanceof ToDo)
                    .collect(Collectors.toList()));
        } else if (type.equals("Deadline")) {
            return new TaskList(tasks
                    .stream()
                    .filter(p -> p instanceof Deadline)
                    .collect(Collectors.toList()));
        } else if (type.equals("Event")) {
            return new TaskList(tasks
                    .stream()
                    .filter(p -> p instanceof Event)
                    .collect(Collectors.toList()));
        } else {
            String formattedType = type.toLowerCase();
            return new TaskList(tasks
                    .stream()
                    .filter(p -> p.toString().toLowerCase().contains(formattedType))
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Formats the TaskList to be readable by the user.
     * @return User-readable format of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String rank = String.valueOf(i + 1);
            list.append(rank).append(".").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                list.append("\n");
            }
        }
        return list.toString();
    }
}
