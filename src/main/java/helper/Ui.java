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
    public void showError(String s) {
        dukePrint(s);
    }

    /**
     * Print with horizontal lines
     * @param s String to print
     */
    public void dukePrint(String s) {
        System.out.println("\n----------------------");
        System.out.println(s);
        System.out.println("----------------------\n");
    }

    /**
     * Print list of tasks
     * @param ls list of tasks
     */
    public void dukePrint(List<Task> ls) {
        System.out.println("\n----------------------");
        for (int i = 0 ; i < ls.size(); i++) {
            System.out.print(i+1);
            System.out.println(". " + ls.get(i) );
        }
        System.out.println("----------------------\n");
    }
}
