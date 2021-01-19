import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DukeList {
    private final List<DukeTask> list;

    public DukeList() {
        this.list = new ArrayList<DukeTask>();
    }

    public DukeList(List<DukeTask> list) {
        this.list = list;
    }

    public void add(String item) {
        this.list.add(new DukeTask(item));
        System.out.println("added: " + item + "\n");
    }

    public void add(DukeTask task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", this.list.size()));
    }

    public void listItems() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (DukeTask items : this.list) {
            System.out.println(String.format("%d.%s", i, items));
            i++;
        }
        System.out.println("");
    }

    public void done(int index) {
        DukeTask task = this.list.get(index - 1).markDone();
        this.list.set(index - 1, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task + "\n");
    }

    public void delete(int index) {
        DukeTask task = this.list.get(index - 1);
        this.list.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", this.list.size()));
    }

    public int size() {
        return this.list.size();
    }
}
