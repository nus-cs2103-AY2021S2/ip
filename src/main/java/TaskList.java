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
        assert storage != null : "storage passed to TaskList cannot be null. ";
        this.storage = storage;
        this.tasks = new ArrayList<>();

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
     * Returns tasks given list of indexes.
     * @param indexes Indexes of tasks wanted
     * @return List of tasks
     */
    public List<Task> get(int[] indexes) {
        List<Task> resultTasks = new ArrayList<>();
        for (int i : indexes) {
            assert i > 0 && i <= this.getSize() : "Index to get should be between 1 and " + this.getSize()
                    + " but given " + i + ". ";
            resultTasks.add(this.tasks.get(i - 1));
        }
        return resultTasks;
    }

    public Task get(int index) {
        return this.tasks.get(index - 1);
    }
    /**
     * Removes task at given index.
     * @param indexes Index of task wanted
     * @return List of tasks that are removed
     */
    public List<Task> remove(int[] indexes) {
        List<Task> removedTasks = new ArrayList<>();
        for (int i : indexes) {
            int actualIndex = i - removedTasks.size(); //size of tasks will change as tasks are removed
            assert actualIndex > 0 : "Index passed to remove must be greater than 1. ";
            assert actualIndex <= this.tasks.size() : "Index passed to remove cannot be greater than length of task list. ";
            this.storage.remove(actualIndex);
            removedTasks.add(this.tasks.remove(actualIndex - 1));
            this.count--;
        }
        return removedTasks;
    }

    /**
     * Replaces task at given index.
     * @param indexes Index of task to be replaced
     * @param tasks New tasks that will replace previous tasks
     */
    public void set(int[] indexes, List<Task> tasks) {
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            Task task = tasks.get(i);
            assert index > 0 : "Index passed to set must be greater than 1. ";
            assert index <= this.tasks.size() : "Index passed to set cannot be greater than length of task list. ";
            assert task != null : "Task passed to set must not be null. ";
            this.storage.set(index, task);
            this.tasks.set(index - 1, task);
        }
    }

    /**
     * Adds given task to end of the task list.
     * @param task Task to be added
     */
    public void add(Task task) {
        assert task != null : "Task to be added must not be null. ";
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

    /**
     * Alternative to find, returns indexes of tasks instead of a list of tasks.
     * @param searchString substring to search for
     * @return int array of indexes of tasks containing searchString
     */
    int[] findIndex(String searchString) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).getName().contains(searchString)) {
                resultList.add(i + 1);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    /**
     * Alternative to list, returns array of all indexes in the taskList.
     * @return int array of indexes of all tasks
     */
    int[] listIndex() {
        int[] result = new int[this.tasks.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i + 1;
        }
        return result;
    }
}
