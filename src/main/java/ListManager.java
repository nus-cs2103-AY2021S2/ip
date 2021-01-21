import java.util.ArrayList;
import java.util.List;

public class ListManager {
    List<Task> list;
    int listSize;

    public ListManager() {
        this.list = new ArrayList<Task>();
        this.listSize = 0;
    }

    /**
     * Adds the task argument into the task list.
     * Prints out a response to indicate successful adding of task.
     * Prints out the current number of tasks in the list.
     * @param task
     * @return void
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
     * @param index
     * @return void
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
     * @param index
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
