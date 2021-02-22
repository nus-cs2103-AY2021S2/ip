package myDuke;

import java.util.ArrayList;
import java.util.List;

/**
 * A task list that contains all tasks inputted e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {

    List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds task to taskList.
     * @param task Task to be added.
     */
    void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Replaces a Task at the specified index in the taskList with a specified Task.
     * @param newTask Task to replace the original.
     * @param index integer value of the index of the Task in the taskList to be replaced.
     */
    void changeTask(Task newTask, int index) {
        taskList.set(index - 1, newTask);
    }

    /**
     * Deletes a Task at the specified index in the taskList.
     * @param index integer value of the index of the Task in the taskList to be deleted.
     */
    void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    /**
     * Retrieves a Task at the specified index in the taskList.
     * @param index integer value of the index of the Task in the taskList to be retrieved.
     * @return Task object at the specified index in the taskList.
     */
    Task getTask(int index) {
        return taskList.get(index - 1);
    }

    /**
     * Retrieves the taskList.
     * @return the taskList.
     */
    List<Task> getTaskList() {
        return this.taskList;
    }

}