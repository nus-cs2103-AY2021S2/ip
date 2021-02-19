package helper;

import java.util.List;

import task.Task;

/**
 * Class to handle the printing and display
 */
public class Ui {

    /**
     * Show error s
     * @param s error
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Print list of tasks
     * @param ls list of tasks
     */
    public String listOfTaskToString(List<Task> ls) {
        StringBuilder initString = new StringBuilder();
        for (int i = 0; i < ls.size(); i++) {
            initString.append((i + 1)).append(". ").append(ls.get(i)).append("\n");
        }
        return initString.toString();
    }
}
