package myDuke;

import java.util.Comparator;

public class TaskListComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.info.compareTo(t2.info);
    }
}
