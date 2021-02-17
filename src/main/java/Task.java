import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
    private boolean done;
    private String name;
    private List<String> tags;

    Task(String name) {
        this.name = name;
        this.done = false;
        this.tags = new ArrayList<>();
    }

    Task(String name, String[] tags) {
        this.name = name;
        this.done = false;
        this.tags = Arrays.asList(tags);
    }

    void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String check = done ? "X" : " ";
        return String.format("[%s] %s", check, name);
    }

    public String toStorageString() {
        if (done) {
            return "Y|" + this.name + "|";
        } else {
            return "N|" + this.name + "|";
        }
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }
}
