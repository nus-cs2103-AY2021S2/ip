package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

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
    public void printList() {
        if (this.taskList.size() == 0) {
            printEmptyList();
        } else {
            printNonEmptyList();
        }
    }

    private void printEmptyList() {
        System.out.println("You have completed all tasks!");
    }

    private void printNonEmptyList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : this.taskList) {
            System.out.println(counter + ". " + t);
            counter++;
        }
    }

    /**
     * Prints the task at the specified position.
     *
     * @param pos position of task to be printed.
     */
    public void printTask(int pos) {
        System.out.println(this.taskList.get(pos));
    }

    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Adds a task to the existing list of tasks.
     *
     * @param t task to be added to the list.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes the task at the specified position from the list.
     *
     * @param pos position of the task to be removed.
     */
    public void deleteTask(int pos) {
        this.taskList.remove(pos);
    }

    /**
     * Prints the number of existing tasks in the list.
     */
    public void printNumTasksInList() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }
}
