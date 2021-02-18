package duke;

import java.util.ArrayList;

/**
 * Custom arraylist for task objects
 */
public class DukeList {

    private final ArrayList<Task> list;
    private int size;

    public DukeList() {
        this.list = new ArrayList<>();
        this.size = 0;
    }

    public DukeList(ArrayList<Task> list) {
        this.list = list;
        size = list.size();
    }

    /**
     * add task into the list
     * @param item Task item
     */
    public void add(Task item) {
        list.add(item);
        size++;
    }

    /**
     * mark task as done
     * @param x task number
     */
    public void markAsDone(int x) {
        list.get(x).markAsDone();
    }

    /**
     * deletes a task from the list
     * @param x Task number to be removed
     */

    public void delete(int x) {
        list.remove(x);
        size--;
    }

    /**
     * deletes all task in the list
     */
    public void deleteAll() {
        if (size > 0) {
            list.subList(0, size).clear();
        }
        size = 0;
    }

    /**
     * Returns the size of the list
     * @return int size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the task from the list
     * @param x Task number x
     * @return Task x
     */
    public Task get(int x) {
        return list.get(x);
    }
}
