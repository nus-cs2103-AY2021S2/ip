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
        System.out.println("Greetings and salutations! I am Duke, a helpful chatbot.\nPlease enter your command(s).");
        System.out.println("To exit, enter the command 'bye'.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }
        System.out.println("Farewell. Hope that you will visit again soon!");

    }
}
