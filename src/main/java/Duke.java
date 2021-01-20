import java.util.Scanner;

public class Duke {
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        horizontalLine();

        final Task store[] = new Task[100];
        int counter = 0;
        final Scanner scan = new Scanner(System.in);
        while (true) {
            final String input = scan.nextLine();
            String[] inputArray = input.split(" ");
            horizontalLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                horizontalLine();
                break;
            }
            
            if (input.equals("")) {

            } else if (input.equals("list")) {
                if (counter == 0) {
                    System.out.println("\t Hmm... You do not have any tasks!");
                }
                for (int i = 0; i < counter; i++) {
                    System.out.printf("\t%d. %s\n", i + 1, store[i]);
                }
            } else if (inputArray[0].equals("done")) {
                if (inputArray.length == 2) {
                    try {
                        final int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (0 <= index && index < counter) {
                            store[index].markAsDone();
                            System.out.println("\tNice! I've marked this task as done:");
                            System.out.printf("\t%s\n", store[index]);
                        } else {
                            System.out.println("\tOpps! The index is out of bound.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\tOpps! Please input a number.");
                    }
                } else {
                    System.out.println("\tPlease follow this format \"done <index>\".");
                }
            } else if (counter >= 100) {
                System.out.println("\tSorry. The database is full!");
            } else {
                store[counter++] = Task.create(input);
                System.out.printf("\tTask added: %s\n", input);
            }

            horizontalLine();
        }
        scan.close();
    }

    private static void horizontalLine() {
        System.out.println("────────────────────────────────────────────────────────────────────");
    }
}
