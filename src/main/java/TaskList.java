import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Storage storage;
    private List<Task> tasks;
    private int count;

    /**
     * Constructor for TaskList, a wrapper class for a list of Tasks and all operations on it. Writes to storage by
     * calling Storage.
     * @param storage Storage object that TaskList writes to.
     */
    public TaskList(Storage storage) {
        assert storage != null: "storage passed to TaskList cannot be null. ";
        this.storage = storage;
        this.tasks = new ArrayList<>();
        assert this.tasks != null: "ArrayList for TaskList not successfully created. ";

        ArrayList<String> storageStrings = this.storage.readStorage();
        storageStrings.forEach(x -> this.tasks.add(parseStorageString(x)));

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

    /**
     * Returns task at given index.
     * @param index Index of task wanted
     * @return Task at given index
     */
    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Removes task at given index.
     * @param index Index of task wanted
     */
    public void remove(int index) {
        assert index > 0: "Index passed to remove must be greater than 1. ";
        assert index <= this.tasks.size(): "Index passed to remove cannot be greater than length of task list. ";
        this.storage.remove(index);
        this.tasks.remove(index - 1);
        this.count--;
    }

    /**
     * Replaces task at given index.
     * @param index Index of task to be replaced
     * @param task New task that will replace previous task
     */
    public void set(int index, Task task) {
        assert index > 0: "Index passed to set must be greater than 1. ";
        assert index <= this.tasks.size(): "Index passed to set cannot be greater than length of task list. ";
        assert task != null: "Task passed to set must not be null. ";
        this.storage.set(index, task);
        this.tasks.set(index - 1, task);
    }

    /**
     * Adds given task to end of the task list.
     * @param task Task to be added
     */
    public void add(Task task) {
        assert task != null: "Task to be added must not be null. ";
        this.storage.add(task);
        this.tasks.add(task);
        this.count++;
    }

    /**
     * Returns number of tasks in task list.
     * @return Number of tasks in list
     */
    public int getSize() {
        return this.count;
    }

    /**
     * Returns all tasks that contains searchString in their descriptions.
     * @param searchString substring to search for
     * @return List of all tasks containing searchString
     */
    public List<Task> find(String searchString) {
        List<Task> resultList = new ArrayList<>();
        tasks.forEach(x -> {
            if (x.getName().contains(searchString)) {
                resultList.add(x);
            }
        });
        return resultList;
    }
}
