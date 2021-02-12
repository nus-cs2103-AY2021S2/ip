package duke.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.compareValue() - t2.compareValue();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
