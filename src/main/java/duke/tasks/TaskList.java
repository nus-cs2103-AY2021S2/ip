package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> converted) {
        this.taskList = new ArrayList<>(converted);
    }

    /**
     * Marks task at specified position to be done.
     *
     * @param pos position of task to be marked.
     */
    public void setTaskDone(int pos) {
        taskList.get(pos).markAsDone();
    }

    /**
     * Prints the list of tasks.
     * If the list is empty, a statement indicating so will be printed.
     */
    public String getListInString () {
        if (this.taskList.size() == 0) {
            return getEmptyListInString();
        }
        return getNonEmptyListInString().toString();
    }

    private String getEmptyListInString() {
        String completedAllTasksMsg = "You have completed all tasks!";
        return completedAllTasksMsg;
    }

    private StringBuilder getNonEmptyListInString() {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : this.taskList) {
            stringBuilder.append("\n" + counter + ". " + task);
            counter++;
        }
        return stringBuilder;
    }

    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Adds a task to the existing list of tasks.
     *
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at the specified position from the list.
     *
     * @param pos position of the task to be removed.
     */
    public void deleteTask(int pos) {
        this.taskList.remove(pos);
    }
}
