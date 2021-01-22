package Duke.Helper;

import java.util.ArrayList;
import Duke.Task.*;

public class Ui {
    public static final String LINES = "____________________________________________________________";
    public void printResponse(String response) {
        System.out.println(LINES);
        System.out.println(response);
        System.out.println(LINES);
        System.out.println();
    }

    public void printAllTask(ArrayList<Task> list){
        System.out.println(LINES);
        if (list.isEmpty()){
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }
}
