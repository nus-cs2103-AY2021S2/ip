import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean isActive;
    private final Scanner scanner;
    private final ArrayList<String> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

    }

    public Duke() {
        isActive = true;
        scanner = new Scanner(System.in);
        list = new ArrayList<>();

        greet();
        listen();
    }

    private void greet() {
        System.out.println("Hello, I'm Duke!\n" + "What can I do for you?\n");
    }

    private void listen() {
        while (isActive) {
            System.out.println("Let me know what to do!");
            String input = scanner.nextLine();

            switch (input) {
            case "bye":
                shutDown();
                break;
            case "list":
                printList();
                break;
            default:
                addToList(input);
                break;
            }
        }
    }

    private void addToList(String item) {
        list.add(item);
        System.out.println("added: " + item + "\n");
    }

    private void printList() {
        int i = 1;
        for (String s : list) {
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println();
    }

    private void shutDown() {
        isActive = false;
        System.out.println("Bye bye, see you soon!\n");
    }
}
