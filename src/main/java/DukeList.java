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

    public void listItems() {
        int i = 1;
        for (DukeTask items : this.list) {
            System.out.println(i + "." + items.getStatusIcon() + " " + items);
            i++;
        }
        System.out.println("");
    }

    public void done(int index) {
        DukeTask task = this.list.get(index - 1).markDone();
        this.list.set(index - 1, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.getStatusIcon() + " " + task);
        System.out.println("");
    }
}
