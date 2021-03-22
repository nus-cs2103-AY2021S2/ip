package duke;

import java.util.ArrayList;
import java.util.List;

import dukeexception.DukeException;
import storage.Storage;

public class TaskList {
    private Storage storage;
    private List<Task> tasks;
    private int numOfTasks;

    /**
     * Constructor for a task list object with a storage for the list of tasks to be stored.
     * @param storage A storage for the list of tasks to be stored.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        ArrayList<String> storedData = this.storage.load();
        for (String s : storedData) {
            this.tasks.add(decode(s));
        }
        this.numOfTasks = this.tasks.size();
    }

    /**
     * Method which decodes stored data from duke.txt into list of tasks for the running of program.
     * @param data The string of data stored in duke.txt.
     * @return The decoded task from the data.
     */
    private Task decode(String data) {
        String[] dataArray = data.split("\\|");
        Task task;


        if (dataArray[0].equals("T")) {
            task = new Todo(dataArray[2]);
        } else if (dataArray[0].equals("D")) {
            task = new Deadline(dataArray[2], dataArray[3]);
        } else {
            task = new Event(dataArray[2], dataArray[3]);
        }
        if (dataArray[1].contains("X")) {
            task.markDone();
        }

        return task;
    }

    public Task get(int taskNumber) throws DukeException {
        try {
            return this.tasks.get(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("The index is out of range.");
        }
    }

    /**
     * Method which checks if the list is empty.
     * @return True if list is empty.
     *         False otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * An add method for the task to be added to the list.
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        this.storage.add(task);
        this.tasks.add(task);
        this.numOfTasks = this.tasks.size();
    }

    /**
     * A remove method for the task to be added to the list.
     * @param index The Index of corresponding task to be removed from the list.
     */
    public void remove(int index) {
        this.storage.remove(index);
        this.tasks.remove(index - 1);
        this.numOfTasks = this.tasks.size();
    }

    /**
     * A set method with index and a task object.
     * @param index The index of a corresponding task to be replaced.
     * @param task The new task set to replace an old task.
     */
    public void set(int index, Task task) {
        this.storage.set(index, task);
        this.tasks.set(index - 1, task);
    }

    /**
     * Method which creates a List of filtered tasks according to the search word.
     * @param searchWord A search word use as a keyword filter for tasks.
     * @return A list of filtered tasks with corresponding search word.
     */
    public List<Task> filterTasks(String searchWord) {
        List<Task> filtered = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchWord)) {
                filtered.add(tasks.get(i));
            }
        }
        return filtered;
    }

    /**
     * Method which retrieves the number of tasks.
     * @return The number of tasks in the list of tasks.
     */
    public int getSize() {
        return this.numOfTasks;
    }

}
