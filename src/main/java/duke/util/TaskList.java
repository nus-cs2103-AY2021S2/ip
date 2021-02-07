package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @param tasks List of tasks.
     */
    public void load(List<Task> tasks) {
        lst.clear();
        lst.addAll(tasks);
    }


    /**
     * Adds task to the tasklist.
     */
    public void addTask(Task t) {
        lst.add(t);
    }

    /**
     * Delete tasks from the tasklist.
     *
     * @param tasksNum Index of tasks to be deleted.
     * @return Tasks that has been deleted.
     * @throws DukeInputException If any of given index is out of range.
     */
    public String[] deleteTask(int[] tasksNum) throws DukeInputException {
        assert tasksNum.length != 0;

        for (int i : tasksNum) {
            if (i < 0 || i >= lst.size()) {
                throw new DukeInputException(String.format("\"%d\" is an invalid number!", i + 1));
            }
        }

        List<String> deletedTasks = new ArrayList<>();

        //Remove in descending index to avoid wrong deletion.
        Arrays.sort(tasksNum);
        for (int i = tasksNum.length - 1; i >= 0; i--) {
            Task deletedTask = lst.remove(tasksNum[i]);
            deletedTasks.add(deletedTask.toString());
        }

        return deletedTasks.toArray(String[]::new);
    }

    /**
     * Mark tasks in tasklist as completed.
     *
     * @param tasksNum Index of tasks that has been completed.
     * @return Tasks that has been completed.
     * @throws DukeInputException If any of the given index is out of range.
     */
    public String[] completeTask(int[] tasksNum) throws DukeInputException {
        assert tasksNum.length != 0;

        for (int i : tasksNum) {
            if (i < 0 || i >= lst.size()) {
                throw new DukeInputException(String.format("\"%d\" is an invalid number!", i + 1));
            }
        }

        List<String> completedTasks = new ArrayList<>();

        for (int i : tasksNum) {
            Task t = lst.get(i);
            Task completedTask = t.markDone();
            lst.set(i, completedTask);
            completedTasks.add(completedTask.toString());
        }

        return completedTasks.toArray(String[]::new);
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
