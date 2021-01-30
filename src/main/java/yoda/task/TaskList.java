package yoda.task;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Specialised list class to handle task-related instructions.
 */
public class TaskList implements Serializable {
    /** List of tasks created by user */
    private List<Task> taskList;

    /**
     * Creates a TaskList object.
     * @param taskList List of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Accesses a task from the list.
     * @param taskNumber Position of task in list.
     * @return Task requested by user
     */
    public Task accessTask(int taskNumber) {
        return taskList.get(taskNumber);
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task in the list as done.
     * @param taskNumber Position of task in the list.
     */
    public void markTaskAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber Position of task in list.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the list
     */
    public int length() {
        return taskList.size();
    }

    /**
     * Filters the tasks in the list by the type requested.
     * @param type Type of task requested.
     * @return List containing only the type of task requested.
     */
    public TaskList filterByTask(String type) {
        if (type.equals("ToDo")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof ToDo)
                    .collect(Collectors.toList()));
        } else if (type.equals("Deadline")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof Deadline)
                    .collect(Collectors.toList()));
        } else if (type.equals("Event")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof Event)
                    .collect(Collectors.toList()));
        } else {
            return this;
        }
    }

    /**
     * Formats the tasklist to be readable by the user.
     * @return User-readable format of the tasklist.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String rank = String.valueOf(i + 1);
            list.append(rank).append(".").append(taskList.get(i));
            if (i != taskList.size() - 1) {
                list.append("\n");
            }
        }
        return list.toString();
    }
}
