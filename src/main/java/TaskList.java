import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    protected final List<Task> lst;
    private final static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public void addItem(Task task) {
        this.lst.add(task);
    }

    public void removeItem(int id) {
        this.lst.remove(id);
    }

    public void doneTask(int id) {
        this.lst.get(id).done();
    }

    /*public void printList() {
        for (int i = 0; i < lst.size(); i++) {
            pw.printf("%d.%s%n", i + 1, lst.get(i));
        }
        pw.flush();
    }*/

    public String getTasksAsString() {
        return lst.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<Task> getLst() {
        return lst;
    }
}
