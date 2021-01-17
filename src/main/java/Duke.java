import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> stringList;
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetUser() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void printList() {
        for (int i = 0; i < stringList.size(); i++) {
            System.out.printf("%d. %s%n", i+1, stringList.get(i));
        }
    }

    private static void addItem(String command) {
        System.out.printf("added: %s%n", command);
        stringList.add(command);
    }

    private static void echoCommand(String command) {
        printHorizontalLine();
        if(command.equals("list")) {
            printList();
        } else {
            addItem(command);
        }
        printHorizontalLine();
    }

    private static void exitCommand() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        greetUser();
        stringList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            echoCommand(command);
        }
        exitCommand();
        sc.close();
    }
}