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
        List<String> list = new ArrayList<>(100);
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
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Farewell. Hope that you will visit again soon!");

    }
}
