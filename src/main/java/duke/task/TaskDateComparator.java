package duke.task;

import java.util.Comparator;

/**
 * Comparator class to facilitate comparison of dates between Task objects.
 */
public class TaskDateComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDate().compareTo(o2.getDate());
    }

}
