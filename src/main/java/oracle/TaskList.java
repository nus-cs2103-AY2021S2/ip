package oracle;

import command.CommandFormatException;
import entry.Deadline;
import entry.Event;
import entry.Task;
import entry.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasklist;

    /** Creates a new TaskList, which stores the tasks. This class provides some abstraction for the underlying
     * ArrayList<Task>
     * @param load given the raw strings from the storage file, we create the tasks
     */
    public TaskList(List<String> load) {
        this.tasklist = new ArrayList<>();
        for (String s : load) {
            String[] sorted = s.split("\u001E");
            Boolean isDone = sorted[1].equals("T");
            try{
                switch (sorted[0]) {
                case "T":
                    this.tasklist.add(new Todo(isDone, sorted[2]));
                    break;
                case "D":
                    this.tasklist.add(new Deadline(isDone, sorted[2], sorted[3]));
                    break;
                case "E":
                    this.tasklist.add(new Event(isDone, sorted[2], sorted[3]));
                }
            } catch (CommandFormatException ignored) {
            }
        }
    }

    /**
     * Create a new TaskList object
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /** Size corresponds to the equivalent operation in the ArrayList object
     * @return size of the ArrayList
     */
    public int size() {
        return this.tasklist.size();
    }

    /** Get corresponds to the equivalent operation in the ArrayList object
     * @param i the index of the task to be gotten
     * @return Task
     */
    public Task get(int i) {
        return this.tasklist.get(i);
    }

    /** MarkDone marks the task indicated by the index in the ArrayList as Done
     * @param i the index of the task to be marked done
     */
    public void markDone(int i) {
        tasklist.get(i).markDone();
    }

    /** Add corresponds to the equivalent operation in the ArrayList object
     * @param task is the Task to be added
     */
    public void add(Task task) {
        this.tasklist.add(task);
    }

    /** Remove corresponds to the equivalent operation in the ArrayList object
     * @param i the index of the task to be removed
     */
    public void remove(int i) {
        this.tasklist.remove(i);
    }

    /**
     * @return the entire TaskList array
     */
    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }
}
