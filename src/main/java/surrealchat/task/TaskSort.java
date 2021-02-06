package surrealchat.task;

import java.util.Comparator;

import surrealchat.exception.SurrealException;

/**
 * Class containing comparators for sorting.
 */
public class TaskSort {
    public static final Comparator<Task> COMPARE_PRIORITY = (
            Task t1, Task t2) -> t2.taskPriority.getPriorityLevel() - t1.taskPriority.getPriorityLevel();
    public static final Comparator<Task> COMPARE_DONE = Comparator.comparingInt(t -> t.getStatusInt());

    /**
     * Obtains the correct Comparator for sorting.
     * @param key The sorting criteria.
     * @return Appropriate Comparator for that criteria.
     * @throws SurrealException If Comparator cannot be found.
     */
    public static Comparator<Task> getComparator(String key) throws SurrealException {
        switch(key) {
        case "priority":
            return TaskSort.COMPARE_PRIORITY;
        case "done":
            return TaskSort.COMPARE_DONE;
        default:
            throw new SurrealException("Sort type not supported or invalid. Not stonks!\n");
        }
    }
}
