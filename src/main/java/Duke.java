package main.java;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Please input a command.");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            System.out.println("  ~~~");
            if (input.equals("bye")) {
                System.out.println("  See you next time.");
                System.out.println("  ~~~");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("  " + (i + 1) + "." + tasks.get(i));
                }
            } else if (input.split(" ")[0].equals("done")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                tasks.get(i - 1).markAsDone();
                System.out.println("  Successfully marked as done:");
                System.out.println("  " + tasks.get(i - 1));
            } else {
                Task inputTask = new Task(input);
                tasks.add(inputTask);
                System.out.println("  added: " + input);
            }
            System.out.println("  ~~~");
        }
    }
}
