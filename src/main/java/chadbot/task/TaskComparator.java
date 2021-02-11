package chadbot.task;

import java.util.Comparator;

/**
 * The TaskComparator class is used to compare two tasks based on the lexicographical ordering of their names.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares the two tasks by the lexicographical ordering of their names.
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer if t1 is lexicographically smaller than t2, and a positive integer otherwise.
     */
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getName().compareTo(t2.getName());
    }

}
