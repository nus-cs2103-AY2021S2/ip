import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

/**
 * Wrapper class for tasklist.
 */
public class TaskList {
    private List<Task> lst;

    /**
     * Creates empty tasklist.
     */
    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Fills tasklist with contents of the given list.
     * @param lst List of tasks.
     */
    public TaskList(List<Task> lst) {
        this.lst = new ArrayList<>(lst);
    }

    /**
     * Adds task to the tasklist.
     */
    public void addTask(Task t) {
        lst.add(t);
    }

    /**
     * Deletes task from the tasklist.
     * @param i Index of task to be deleted.
     * @return Task that has been deleted.
     * @throws DukeInputException If the given index is out of range.
     */
    public Task deleteTask(int i) throws DukeInputException {
        Task t;

        try {
            t = lst.get(i);
            lst.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInputException(String.format("\"%d\" is an invalid number!", i));
        }

        return t;
    }

    /**
     * Marks task in tasklist as completed.
     * @param i Index of task that has been completed.
     * @return Task that has been completed.
     * @throws DukeInputException If the given index is out of range.
     */
    public Task completeTask(int i) throws DukeInputException {
        Task t;

        try {
            t = lst.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInputException(String.format("\"%d\" is an invalid number!", i));
        }
        t.completed();

        return t;
    }

    /**
     * Retrieve list of tasks on the given date.
     * @param d Date of tasks.
     * @return List of tasks.
     */
    public List<Task> retrieveByDate(LocalDate d) {
        List<Task> results = new ArrayList<>();

        for (Task t : lst) {
            if (d.equals(t.date)) {
                results.add(t);
            }
        }

        return results;
    }

    /**
     * Convert tasks to a numbered list of strings.
     * @return List of numbered task.
     */
    public List<String> listOutTask() {
        List<String> stringlst = new ArrayList<>();
        int counter = 1;

        for (Task t : lst) {
            stringlst.add(String.format("%d. %s", counter++, t.toString()));
        }
        
        return stringlst;
    }

    /**
     * Returns size of tasklist.
     * @return Size of tasklist.
     */
    public int size() {
        return lst.size();
    }

    /**
     * Returns list of tasks.
     * @return List of tasks.
     */
    public List<Task> toList() {
        return new ArrayList<>(lst);
    }

}
