import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Wrapper class for Checklist
 */
public class TaskList {
    private List<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public TaskList(List<Task> lst) {
        this.lst = new ArrayList<>(lst);
    }

    public void addTask(Task t) {
        lst.add(t);
    }

    public Task deleteTask(int i) throws DukeException {
        Task t;
        try {
            t = lst.get(i);
            lst.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\"%d\" is an invalid number!", i));
        }
        return t;
    }

    public Task completeTask(int i) throws DukeException {
        Task t;
        try {
            t = lst.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\"%d\" is an invalid number!", i));
        }
        t.completed();
        return t;
    }

    public List<String> searchByDate(LocalDate d) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            if (d.equals(lst.get(i).date)) {
                results.add(String.format("%d. %s", i + 1, lst.get(i).toString()));
            }
        }

        return results;
    }

    public List<String> listOutTask() {
        List<String> stringlst = new ArrayList<>();
        int counter = 1;
        for (Task t : lst) {
            stringlst.add(String.format("%d. %s", counter++, t.toString()));
        }
        return stringlst;
    }

    public int size() {
        return lst.size();
    }

    public List<Task> toList() {
        return new ArrayList<>(lst);
    }

    public List<String> search(String keyword) {
        try {
            LocalDate date = LocalDate.parse(keyword);
            return searchByDate(date);
        } catch (DateTimeParseException e) {

            List<String> results = new ArrayList<>();

            for (int i = 0; i < lst.size(); i++) {
                if (lst.get(i).getDescription().contains(keyword)) {
                    results.add(String.format("%d. %s", i + 1, lst.get(i).toString()));
                }
            }
    
            return results;
        }
    }
    
}
