package duke;

import java.util.ArrayList;

import duke.commands.Task;

/**
 * Represents the user's list of tasks.
 * Task objects are stored in the object
 */
public class TaskList {
    private final ArrayList<Task> userList;

    /**
     * Constructor for TaskList
     *
     * @param userList is an arrayList of Tasks
     * @see ArrayList
     */
    public TaskList(ArrayList<Task> userList) {
        this.userList = userList;
    }

    /**
     * Overloaded constructor for TaskList object.
     * Used when no ArrayList of Task is passed in as a Parameter.
     */
    public TaskList() {
        this.userList = new ArrayList<>();
    }

    /**
     * Returns the user's TaskList.
     *
     * @return userList.
     */
    public ArrayList<Task> getTaskList() {
        return this.userList;
    }

    /**
     * Returns size of the taskList.
     *
     * @return length of the user's list.
     */
    public int getTaskListSize() {
        return this.userList.size();
    }

    /**
     * Returns task at specified index.
     *
     * @param index Task at the index in the TaskList.
     * @return Task task at given index.
     */
    public Task getTask(int index) {
        return this.userList.get(index);
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param task task to be added into the list.
     */
    public void addTask(Task task) {
        this.userList.add(task);
    }

    /**
     * Removes a task at the particular index of the task list.
     *
     * @param taskIndex index of the task to be deleted in the task list.
     * @return The task that got removed.
     */
    public Task removeTask(int taskIndex) {
        return this.userList.remove(taskIndex);
    }

    public boolean checkTaskPresent(Task task) {
        boolean isPresent = false;
        for (Task userTask : userList) {
            if (userTask.toString().equals(task.toString())) {
                isPresent = true;
            }
        }
        return isPresent;
    }

}
