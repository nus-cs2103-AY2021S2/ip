import java.util.ArrayList;
import java.util.function.Consumer;

public class DukeList {
    private ArrayList<String> list;

    public DukeList() {
        this.list = new ArrayList<String>();
    }

    public void add(String item) {
        list.add(item);
    }

    public void print() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + list.get(i));
        }
    }
}
