import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanf;
    public Ui() {
        scanf = new Scanner(System.in);
    }

    public void printResponse(String msg){
        String response = ">>Duke : " + msg;
        System.out.println(response);
    }
    public String readLine() {
        String input = scanf.nextLine();
        return input;
    }
    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

}

