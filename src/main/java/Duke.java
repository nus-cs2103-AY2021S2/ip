import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetUser() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void echoCommand(String command) {
        printHorizontalLine();
        System.out.println(command);
        printHorizontalLine();
    }

    private static void exitCommand() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            echoCommand(command);
        }
        exitCommand();
        sc.close();
    }
}