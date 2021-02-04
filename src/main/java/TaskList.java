import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Storage storage;
    private List<Task> tasks;
    private int count;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();

        ArrayList<String> storageStrings = this.storage.readStorage();
        for (String s : storageStrings) {
            this.tasks.add(parseStorageString(s));
        }

        this.count = this.tasks.size();
    }

    private Task parseStorageString(String storageString) {
        String[] splitStorageString = storageString.split("\\|");
        Task task;
        if (splitStorageString[0].equals("T")) {
            task = new Todo(splitStorageString[2]);
        } else if (splitStorageString[0].equals("D")) {
            task = new Deadline(splitStorageString[2], splitStorageString[3]);
        } else {
            task = new Event(splitStorageString[2], splitStorageString[3]);
        }

        if (splitStorageString[1].equals("Y")) {
            task.markDone();
        }

        return task;
    }

    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    public void remove(int index) {
        this.storage.remove(index);
        this.tasks.remove(index - 1);
        this.count--;
    }

    public void set(int index, Task task) {
        this.storage.set(index, task);
        this.tasks.set(index - 1, task);
    }

    public void add(Task task) {
        this.storage.add(task);
        this.tasks.add(task);
        this.count++;
    }

    public int getSize() {
        return this.count;
    }

}
