package helper;

import task.Task;

import java.util.List;

public class Ui {

    public Ui() {

    }

    public void showError(String s) {
        dukePrint(s);
    }

    public void dukePrint(String s) {
        System.out.println("\n----------------------");
        System.out.println(s);
        System.out.println("----------------------\n");
    }

    public void dukePrint(List<Task> ls) {
        System.out.println("\n----------------------");
        for (int i = 0 ; i < ls.size(); i++) {
            System.out.print(i+1);
            System.out.println(". " + ls.get(i) );
        }
        System.out.println("----------------------\n");
    }
}
