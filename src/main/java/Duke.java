import java.util.Scanner;

public class Duke {
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        horizontalLine();

        final String store[] = new String[100];
        int counter = 0;
        final Scanner scan = new Scanner(System.in);
        while (true) {
            final String input = scan.nextLine();
            horizontalLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                horizontalLine();
                break;
            }

            if (input.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.printf("\t%d. %s\n", i + 1, store[i]);
                }
            } else if (counter >= 100) {
                System.out.println("\tSorry. The database is full!");
            } else {
                store[counter++] = input;
                System.out.printf("\tadded: %s\n", input);
            }

            horizontalLine();
        }
        scan.close();
    }

    private static void horizontalLine() {
        System.out.println("────────────────────────────────────────────────────────────────────");
    }
}
