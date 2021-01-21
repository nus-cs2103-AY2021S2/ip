import java.util.ArrayList;
import java.util.List;

public class ListManager {
    List<Task> list;
    int listSize;

    public ListManager() {
        this.list = new ArrayList<Task>();
        this.listSize = 0;
    }

    void addTask(Task t) {
        list.add(t);
        listSize++;

        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + t);
        System.out.printf("    Now you have %d tasks in the list.\n", listSize);
    }

    void removeTask(int index) {
        Task temp = list.get(index - 1);
        list.remove(index - 1);
        listSize--;

        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + temp);
        System.out.printf("    Now you have %d tasks in the list.\n", listSize);
    }

    void markTaskAsDone(int index) {
        Task temp = list.get(index - 1);
        temp.markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + temp);
    }

    void displayList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.printf("    %d.%s\n", i, list.get(i - 1));
        }
    }
    
}
