package com.jetbrains;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke! \n " +
                "What would you like to do today? \n" +
                "********************************");
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                displayList(list);
                System.out.println("\n");
            } else if (input.contains("done")) {
                String[] doneCommand = input.split(" ");
                Task task = list.get(Integer.parseInt(doneCommand[1]) - 1);
                System.out.println("Good job! I've marked this task as done:\n    " +
                        task.markDone() +
                        "\n");
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input + "\n");
            }
            input = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye! Stay on task!");
    }

    static void displayList(ArrayList<Task> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n",i + 1, list.get(i).toString());
        }
    }
}

class Task {
    String task;
    boolean isDone;

    Task(String task) {
        this.task = task;
        isDone = false;
    }

    Task markDone() {
        isDone = true;
        return this;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
