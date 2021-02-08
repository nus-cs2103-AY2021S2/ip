package Helper;

import mainclasses.Task;

import java.util.Comparator;

public class SortByDateTime implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b)
    {
        if (a.getDate().compareTo(b.getDate()) > 0) {
            return 1;
        } else if (a.getDate().compareTo(b.getDate()) < 0) {
            return -1;
        } else {
            return a.getTime().compareTo(b.getTime());
        }
    }
}
