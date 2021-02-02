import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;
    int listSize;

    /**
     * Initialises a newly created TaskList object
     * so that it represents an empty List<Task>.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.listSize = 0;
    }

    /**
     * Initialises a newly created TaskList object
     * so that it represents a List of Tasks
     * as given in the argument list.
     *
     * @param list a List<Task> containing Task objects.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        this.listSize = list.size();
    }

    /**
     * Adds the task argument into the task list.
     * Prints out a response to indicate successful adding of task.
     * Prints out the current number of tasks in the list.
     *
     * @param task a Task object to be added into the TaskList
     */
    void addTask(Task task) {
        list.add(task);
        listSize++;

        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.printf("    Now you have %d tasks in the list.\n", listSize);
    }

    /**
     * Removes the task of the given index from the task list.
     * Prints out a response to indicate successful removing of task.
     * Prints out the current number of tasks in the list.
     *
     * @param index the 1-based index of the Task object to be removed.
     */
    void removeTask(int index) {
        Task temp = list.get(index - 1);
        list.remove(index - 1);
        listSize--;

        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + temp);
        System.out.printf("    Now you have %d tasks in the list.\n", listSize);
    }

    /**
     * Marks the task of the given index as done.
     * Prints out a response to indicate successful marking of task.
     *
     * @param index the 1-based index of the Task object to be removed.
     */
    void markTaskAsDone(int index) {
        Task temp = list.get(index - 1);
        temp.markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + temp);
    }

    /**
     * Displays list by iterating through the list members.
     */
    void displayList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.printf("    %d.%s\n", i, list.get(i - 1));
        }
    }
    
}
