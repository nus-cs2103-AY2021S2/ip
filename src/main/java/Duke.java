package main.java;

import main.java.Entity.*;

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
        while (sc.hasNextLine()) {
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
            if (input.toLowerCase().split(" ")[0].equals("todo")) {
                String todoName = input.substring(5);
                Task task = new Todo(todoName);
                list.add(task);
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + task);
                System.out.println("    Now you have " + list.size() + " tasks in the list.");
                continue;
            }
            if (input.toLowerCase().split(" ")[0].equals("deadline")) {
                String[] arr = input.split(" /");
                String name = arr[0].substring(9);
                String[] arr2 = arr[1].split(" ", 2);
                Task task = new Deadline(name, arr2[1], arr2[0]);
                list.add(task);
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + task);
                System.out.println("    Now you have " + list.size() + " tasks in the list.");
                continue;
            }
            if (input.toLowerCase().split(" ")[0].equals("event")) {
                String[] arr = input.split(" /");
                String name = arr[0].substring(6);
                String[] arr2 = arr[1].split(" ", 2);
                Task task = new Event(name, arr2[1], arr2[0]);
                list.add(task);
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + task);
                System.out.println("    Now you have " + list.size() + " tasks in the list.");
                continue;
            }
//            System.out.println("    added: " + input);
//            list.add(new Task(input));
            System.out.println("    Unrecognizable input: " + input);
        }
        System.out.println("Bye. Until next time!");
    }
}
