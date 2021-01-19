package main.java;

import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;

import main.java.subfiles.TaskManager;

import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager= new TaskManager();

    private static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void inputDone(String s) {
        try {
            taskManager.markDone(s);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputDelete(String s) {
        try {
            taskManager.deleteTask(s);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputAdd(String s) {
        try {
            taskManager.addTask(s);
        } catch (EmptyDescriptionException | EmptyTimeException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean executeInput(String s) {
        String[] sArray = s.split(" ");

        if (sArray[0].equals("bye")) {
            return false;
        } else if (sArray[0].equals("list")) {
            taskManager.printTasks();
        } else if (sArray[0].equals("done")) {
            inputDone(sArray[1]);
        } else if (sArray[0].equals("delete")) {
            inputDelete(sArray[1]);
        } else {
            inputAdd(s);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean hasInput = true;

        greet();
        while (hasInput) {
            String s = sc.nextLine();
            hasInput = executeInput(s);
        }
        exit();

        sc.close();
    }
}
