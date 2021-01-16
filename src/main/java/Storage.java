import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<String> storage;

    public Storage() {
        storage = new ArrayList<>();
    }

    public List<String> getStorage() {
        return this.storage;
    }

    public void add(String input) {
        this.storage.add(input);
    }
}
