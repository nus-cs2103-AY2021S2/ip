import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> inputList = new ArrayList<>();
    private static final String BYE = "bye";
    private static final String LIST = "list";

    private static void introduction() {
        String loading = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println(loading);
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    private static void printList() {
        int counter = 1;
        for (String s : inputList) {
            System.out.println(counter + ". " + s);
            counter++;
        }
    }

    private static void endProgram() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
        scanner.close();
    }

    private static void run() {
        introduction();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals(BYE)) {
                endProgram();
                break;
            } else if (input.equals(LIST)) {
                printList();
            } else {
                inputList.add(input);
                System.out.println("added: " + input);
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}