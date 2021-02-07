package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wrapper class for tasklist.
 */
public class TaskList {
    private ObservableList<Task> lst;

    /**
     * Creates empty tasklist.
     */
    public TaskList() {
        lst = FXCollections.observableArrayList();
    }

    /**
     * Fills tasklist with contents of the given list.
     *
     * @param lst List of tasks.
     */
    public TaskList(List<Task> lst) {
        this.lst = FXCollections.observableArrayList(lst);
    }

    /**
     * Adds task to the tasklist.
     */
    public void addTask(Task t) {
        lst.add(t);
    }

    /**
     * Deletes task from the tasklist.
     *
     * @param i Index of task to be deleted.
     * @return Task that has been deleted.
     * @throws DukeInputException If the given index is out of range.
     */
    public Task deleteTask(int i) throws DukeInputException {
        Task t;

        try {
            t = lst.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInputException(String.format("\"%d\" is an invalid number!", i - 1));
        }

        return t;
    }

    /**
     * Marks task in tasklist as completed.
     *
     * @param i Index of task that has been completed.
     * @return Task that has been completed.
     * @throws DukeInputException If the given index is out of range.
     */
    public Task completeTask(int i) throws DukeInputException {
        Task t;

        try {
            t = lst.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInputException(String.format("\"%d\" is an invalid number!", i - 1));
        }

        Task completedTask = t.markDone();
        lst.set(i, completedTask);

        return completedTask;
    }

    /**
     * Convert tasks to a numbered list of strings.
     *
     * @return List of numbered tasks.
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
     *
     * @return Size of tasklist.
     */
    public int size() {
        return lst.size();
    }

    /**
     * Returns list of tasks.
     *
     * @return List of tasks.
     */
    public ObservableList<Task> getList() {
        return lst;
    }

    /**
     * Returns list of tasks on the given date.
     *
     * @param d Date of task.
     * @return List of numbered tasks with the following date.
     */
    private List<String> searchByDate(LocalDate d) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            if (d.equals(lst.get(i).getDate())) {
                results.add(String.format("%d. %s", i + 1, lst.get(i)));
            }
        }

        return results;
    }

    /**
     * Returns list of tasks with the given keyword.
     * <br>If keyword is in a valid LocalDate format (YYYY-MM-DD), search by date, else search by keyword.
     *
     * @param keyword Keyword to search.
     * @return List of numbered tasks containing the given keyword.
     */
    public List<String> search(String keyword) {

        try {
            LocalDate date = LocalDate.parse(keyword);
            return searchByDate(date);
        } catch (DateTimeParseException e) {
            // Keyword is not a date
        }

        List<String> results = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getDescription().contains(keyword)) {
                results.add(String.format("%d. %s", i + 1, lst.get(i)));
            }
        }
        return results;
    }
}
