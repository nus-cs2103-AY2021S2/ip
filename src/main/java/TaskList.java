import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

/**
 * Wrapper class for Checklist
 */
public class TaskList {
    List<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public TaskList(List<Task> lst) {
        this.lst = new ArrayList<>(lst);
    }

    public void addTask(Task t) {
        lst.add(t);
    }

    public void deleteTask(int i) {
        lst.remove(i);
    }

    public List<Task> retrieveByDate(LocalDate d) {
        List<Task> results = new ArrayList<>();
        for (Task t : lst) {
            if (d.equals(t.date)) {
                results.add(t);
            }
        }
        return results;
    }
    
}
