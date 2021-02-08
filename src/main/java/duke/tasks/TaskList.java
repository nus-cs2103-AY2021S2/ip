package duke.tasks;

import java.util.ArrayList;
import java.util.List;

import duke.utils.TaskStringConverter;


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
        return getNonEmptyListInString();
    }

    private String getEmptyListInString() {
        assert this.taskList.size() == 0;

        String completedAllTasksMsg = "You have completed all tasks!";
        return completedAllTasksMsg;
    }

    private String getNonEmptyListInString() {
        String allTasks = TaskStringConverter.stringTasksForProgram(this.taskList);

        return allTasks;
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
        assert pos >= 0 && pos < this.taskList.size();

        this.taskList.remove(pos);
    }
}
