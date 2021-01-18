import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class List {
    private final ArrayList<String> list;
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public List() {
        this.list = new ArrayList<>();
    }

    public void addItem(String string) {
        this.list.add(string);
    }

    public void printList() {
        for(int i = 0; i < list.size(); i++) {
            pw.printf("%d %s%n", i + 1, list.get(i));
        }
        pw.flush();
    }
}
