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
        System.out.println("This is\n " + logo);
        String greetings = "Dear user, welcome to the world of duke!";
        System.out.println(greetings);
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + list.get(i));
                }
                continue;
            }
            System.out.println("    added: " + input);
            list.add(input);
        }
        System.out.println("Bye. Until next time!");
    }
}
