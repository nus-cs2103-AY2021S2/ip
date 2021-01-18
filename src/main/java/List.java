import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class List {
    protected final ArrayList<Task> lst;
    private final static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public List() {
        this.lst = new ArrayList<>();
    }

    public void addItem(Task task) {
        this.lst.add(task);
    }

    public void printList() {
        for(int i = 0; i < lst.size(); i++) {
            pw.printf("%d.%s %s%n", i + 1, lst.get(i).getStatusIcon(), lst.get(i).description);
        }
        pw.flush();
    }
}
