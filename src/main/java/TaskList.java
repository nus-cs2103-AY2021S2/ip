import java.util.ArrayList;

/**
 * Manages a list of Tasks with the ability to add/remove tasks.
 */
public class TaskList {
    protected ArrayList<Task> list;
    protected int numItems;

    /**
     * Constructor for this TaskList object.
     */
    TaskList() {
        this.list = new ArrayList<Task>();
        this.numItems = 0;
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return the size of the TaskList.
     */
    public int getNumItems() {
        return this.numItems;
    }

    /**
     * Adds a Todo Task to the TaskList
     * @param  str description of the task.
     */
    public void addTodo(String str) {
        list.add(numItems, new Todo(str));
        numItems++;
    }

    /**
     * Adds a Deadline Task to the TaskList
     * @param  str description of the task.
     * @param  by  deadline of task.
     */
    public void addDeadline(String str, String by) {
        list.add(numItems, new Deadline(str, by));
        numItems++;
    }

    /**
     * Adds an Event Task to the TaskList
     * @param  str description of the task.
     * @param  at  location of Event task.
     */
    public void addEvent(String str, String at) {
        list.add(numItems, new Event(str, at));
        numItems++;
    }

    /**
     * Prints all tasks in the TaskList
     */
    public void printTasks() {
        for (int i = 0; i < numItems; i++) {
            System.out.println(i+1 + "." + list.get(i));
        }
    }

    /**
     * Returns the task at the given index in the TaskList.
     * @param n index of the wanted task.
     * @return task at the given index in the TaskList.
     */
    public Task getAtInd(int n) {
        return list.get(n);
    }

    /**
     * Deletes the task at the given index in the TaskList.
     * @param n index of the task to be deleted.
     */
    public void deleleTask(int n) {
        list.remove(n);
        numItems--;
    }
}
