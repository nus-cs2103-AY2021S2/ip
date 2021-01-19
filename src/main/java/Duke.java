package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings and salutations! I am Duke, a helpful chatbot.\nPlease enter your command(s).");
        System.out.println("To view the list of tasks, enter the command 'list'.");
        System.out.println("To exit, enter the command 'bye'.");
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                StringBuilder output = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    output.append(i).append(". ").append(list.get(i - 1)).append("\n");
                }
                System.out.println(output);
            } else if (input.substring(0, 4).equals("done")) {
                try {
                    int pointer = Integer.parseInt(input.substring(5)) - 1;
                    Task task = list.get(pointer);
                    task.markAsDone();
                    list.set(pointer, task);
                    System.out.println("Sweet! I've marked this task as done:\n" + list.get(pointer));
                } catch (NumberFormatException e) {
                    System.out.println("Number format exception, enter a number from 1-100!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println ("Array index is out of bounds, enter a number from 1-100!");
                }
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println("Farewell. Hope that you will visit again soon!");

    }
}
