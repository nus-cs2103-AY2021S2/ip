package tasklist;

import java.util.ArrayList;

import exceptions.InvalidArgumentException;
import format.Ui;
import tasks.Task;


/**
 * TaskList class that abstracts away arrayList that contains tasks and all task operations in this app.
 * Most error checking is handled before calling the functions here, but formatting using Ui functions
 * are still done in this class' methods.
 */
public class TaskList {
    private final ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskArrayList.add(t);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task get(int i) {
        return this.taskArrayList.get(i);
    }

    public boolean isEmpty() {
        return this.taskArrayList.isEmpty();
    }


    /**
     * Adds task to array list and prints success message with task details
     *
     * @param t task object to add
     */
    public String addTask(Task t) {
        taskArrayList.add(t);

        return Ui.formatMultiLineMessages(
                "Success! I've added this task:",
                t.toString()
        );
    }

    /**
     * Deletes a task in the list
     *
     * @param i index of task to be deleted
     */
    public String deleteTask(int i) {
        return Ui.formatMultiLineMessages(
                "Got you. I've deleted this task:",
                taskArrayList.remove(i - 1)
        );
    }


    /**
     * Marks a task in the list done
     *
     * @param i index of task to mark done
     * @throws InvalidArgumentException
     */
    public String markDone(int i) {

        taskArrayList.get(i - 1).markAsDone();

        return Ui.formatMultiLineMessages(
                "Good work! I've marked this task done:",
                taskArrayList.get(i - 1)
        );
    }


    /**
     * Finds tasks whose description match a user-inputted string, and prints all
     * matching tasks.
     *
     * @param s Search keyword, inputted by user
     */
    public String findTasks(String s) {
        TaskList filtered = new TaskList();
        for (Task t : taskArrayList) {
            if (t.getDescription().contains(s)) {
                filtered.add(t);
            }
        }

        return Ui.stringifyTaskList(filtered);
    }
}
