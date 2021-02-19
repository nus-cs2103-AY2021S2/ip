package duke;

import java.util.ArrayList;

/**
 * Provides class that supports methods to interact with the taskList
 */
public class TaskList {
    public static int taskListSize;
    private ArrayList<Task> taskList;

    /**
     * Initialises TaskList object.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds todo task to the current taskList and updates taskListSize.
     *
     * @param task todo task to be added into taskList.
     * @return Todo task object of the added task.
     */
    public Task addTodoTask(Todo task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    /**
     * Adds deadline task to the current taskList and updates taskListSize.
     *
     * @param task deadline task to be added into taskList.
     * @return Deadline task object of the added task.
     */
    public Task addDeadlineTask(Deadline task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    /**
     * Adds event task to the current taskList and updates taskListSize.
     *
     * @param task event task to be added into taskList.
     * @return Event task object of the added task.
     */
    public Task addEventTask(Event task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    /**
     * Deletes task at specified index from the current taskList and updates taskListSize.
     *
     * @param index the index of the task to be removed.
     * @return Task object that was deleted.
     */
    public Task deleteTask(int index) {
        assert index >= 0;
        Task task = taskList.get(index);
        taskList.remove(index);
        taskListSize = taskList.size();
        return task;
    }

    /**
     * Marks task as done at the specified index from the current taskList.
     *
     * @param index the index of the task to be marked as done.
     * @return Task object that was marked as done.
     */
    public Task markTaskDone(int index) {
        assert index >= 0;
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Updates the task at specified index by changing description to new one.
     *
     * @param index index of task.
     * @param newDescription new description user wants to change to.
     * @return updated Task object.
     */
    public Task updateTask(int index, String newDescription) {
        assert index >= 0;
        assert newDescription != null;
        Task task = taskList.get(index);
        taskList.remove(index);
        task.updateDescription(newDescription);
        taskList.add(index, task);
        return task;
    }

    /**
     * Finds a list of tasks from the current taskList that contains the specified keyword.
     *
     * @param keyword the keyword provided by the user to search for specific tasks.
     * @return an array list of tasks that contains the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null;
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the current taskList as an array list object.
     *
     * @return Array list object of all current tasks in the task list.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }
}
