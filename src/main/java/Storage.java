import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> history = new ArrayList<>();
        return history;

    }
}
