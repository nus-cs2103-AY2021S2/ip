package Duke.Helper;

import java.util.ArrayList;

import Duke.Constant.Constants;
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
            System.out.println(Constants.EMPTY_TASK_LIST);
        } else {
            System.out.println(Constants.START_LISTING);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }

    public void printMatchedTask(ArrayList<Task> list){
        System.out.println(LINES);
        if (list.isEmpty()){
            System.out.println(Constants.FIND_FAIL);
        } else {
            System.out.println(Constants.FIND_SUCCESS);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }
}
