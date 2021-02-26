import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.isTimedTask() && !t2.isTimedTask()) {
            return -1;
        } else if (!t1.isTimedTask() && t2.isTimedTask()) {
            return 1;
        } else if (t1.isTimedTask() && t1.isTimedTask()) {
            return t1.date.compareTo(t2.date);
        } else {
            return 0;
        }
    }
}
