import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that handles the backend of the program,
 * storing all the tasklists of the user and handles
 * each command.
 */

public class TaskList {

    private ArrayList<Task> storage;
    private Set<Task> duplicateChecker;

    public TaskList() {
        this.storage = new ArrayList<>();
        this.duplicateChecker = new HashSet<>();
        duplicateChecker.addAll(storage);
    }

    ArrayList<Task> getStorage() {
        return this.storage;
    }

    boolean checkForDuplicate(Set<Task> tasks, Task task) {
        int originalCount = tasks.size();
        tasks.add(task);
        int postCount = tasks.size();
        return originalCount == postCount;
    }

    Set<Task> getDuplicateChecker() {
        return this.duplicateChecker;
    }
}


