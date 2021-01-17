import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> listOfTasks = new ArrayList<>();

    private static void printAllTasks() {
        int counter = 1;
        for (String s : listOfTasks) {
            System.out.println(counter + ". " + s);
            counter++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            if (input.equals("bye")) break;
            if (input.equals("list")) {
                printAllTasks();
            } else {
                System.out.println("added: " + input + "\n");
                listOfTasks.add(input);
            }
        } while(true);
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
