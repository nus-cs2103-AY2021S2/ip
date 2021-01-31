package yoda.task;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Specialised list class to handle task-related instructions.
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
     * Accesses a task from the list.
     * @param taskNumber Position of task in list.
     * @return Task requested by user
     */
    public Task accessTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the list as done.
     * @param taskNumber Position of task in the list.
     */
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber Position of task in list.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the list
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Filters the tasks in the list by the type or keyword requested.
     * @param type Type of task requested.
     * @return TaskList containing tasks of the type requested or tasks with the
     * keyword requested.
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
     * Formats the tasklist to be readable by the user.
     * @return User-readable format of the tasklist.
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
