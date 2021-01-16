package main.java;

import main.java.Entity.Task;

import java.util.ArrayList;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("This is\n " + logo);
        String greetings = "Dear user, welcome to the world of duke!";
        System.out.println(greetings);
        List<Task> list = new ArrayList<>();
        Set<Integer> done = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                if (list.size() == 0) {
                    System.out.println("    Currently there is nothing on your list.");
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + list.get(i));
                }
                continue;
            }
            if (input.toLowerCase().split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = list.get(index);
                task.markAsDone();
                System.out.println("    Nice! I've mark this task as done:");
                System.out.println("        " + task);
                continue;
            }
            System.out.println("    added: " + input);
            list.add(new Task(input));
        }
        System.out.println("Bye. Until next time!");
    }
}
