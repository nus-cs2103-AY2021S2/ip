package main.java;

import main.java.exceptions.*;
import main.java.subfiles.*;
import java.util.Scanner;

public class Duke {
    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        greet();

        while (true) {
            String s = sc.nextLine();
            String[] sArray = s.split(" ");

            if (sArray[0].equals("bye"))
                break;
            else if (sArray[0].equals("list"))
                taskManager.printTasks();
            else if (sArray[0].equals("done"))
                taskManager.markDone(Integer.parseInt(sArray[1]) - 1);
            else {
                try {
                    taskManager.addTask(s);
                } catch (EmptyDescriptionException | EmptyTimeException | InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        exit();
        sc.close();
    }
}
