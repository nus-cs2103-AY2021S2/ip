package main.java;

import main.java.subfiles.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        taskManager.greet();

        while (true) {
            String s = sc.nextLine();
            String[] sArray = s.split(" ");

            if (sArray[0].equals("bye"))
                break;
            else if (sArray[0].equals("list"))
                taskManager.printTasks();
            else if (sArray[0].equals("done"))
                taskManager.markDone(Integer.parseInt(sArray[1]) - 1);
            else
                taskManager.addTask(s);
        }

        taskManager.exit();
        sc.close();
    }
}
