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

    public int getNumItems() {
        return this.numItems;
    }

    public void addTodo(String str) {
        list.add(numItems, new Todo(str));
        numItems++;
    }

    public void addDeadline(String str, String by) {
        list.add(numItems, new Deadline(str, by));
        numItems++;
    }

    public void addEvent(String str, String at) {
        list.add(numItems, new Event(str, at));
        numItems++;
    }

    public void printTasks() {
        for (int i = 0; i < numItems; i++) {
            System.out.println(i+1 + "." + list.get(i));
        }
    }

    public Task getAtInd(int n) {
        return list.get(n);
    }

    public void deleleTask(int n) {
        list.remove(n);
        numItems--;
    }
}
