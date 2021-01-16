package main.java;

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
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            System.out.println("    " + input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Until next time!");
    }
}
