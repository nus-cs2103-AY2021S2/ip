package main.java;

import java.util.Scanner;

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
        while (true) {
            String input = sc.nextLine();
            System.out.println("  ~~~");
            if (input.equals("bye")) {
                System.out.println("  See you next time.");
                System.out.println("  ~~~");
                break;
            }
            System.out.println("  " + input);
            System.out.println("  ~~~");
        }
    }
}
