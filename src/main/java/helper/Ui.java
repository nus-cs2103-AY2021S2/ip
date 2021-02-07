package helper;

import task.Task;

import java.util.List;

/**
 * Class to handle the printing and display
 */
public class Ui {

    public Ui() {

    }

    /**
     * Show error s
     * @param s error
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Print with horizontal lines
     * @param s String to print
     */
    public String dukePrint(String s) {
        System.out.println("\n----------------------");
        System.out.println(s);
        System.out.println("----------------------\n");
        return s;
    }

    /**
     * Print list of tasks
     * @param ls list of tasks
     */
    public String dukePrint(List<Task> ls) {
        StringBuilder initString = new StringBuilder();
        for (int i = 0; i < ls.size(); i++) {
            initString.append((i + 1)).append(". ").append(ls.get(i)).append("\n");
        }
        return initString.toString();
    }
}
