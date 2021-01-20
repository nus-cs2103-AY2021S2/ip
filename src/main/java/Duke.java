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
            final String[] tokens = input.split(" ");
            boolean isInsert = false;
            horizontalLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                horizontalLine();
                break;
            }

            if (input.equals("")) { // empty string
                System.out.println("\t...");
            } else if (input.equals("list")) { // list tasks
                if (counter == 0) {
                    System.out.println("\tHmm... You do not have any tasks!");
                } else {
                    System.out.println("\tHere are the tasks in your list:");
                }
                for (int i = 0; i < counter; i++) {
                    System.out.printf("\t%d. %s\n", i + 1, store[i]);
                }
            } else if (tokens[0].equals("done")) { // mark task as done
                if (tokens.length == 2) {
                    try {
                        final int index = Integer.parseInt(tokens[1]) - 1;
                        if (0 <= index && index < counter) {
                            store[index].markAsDone();
                            System.out.println("\tNice! I've marked this task as done:");
                            System.out.printf("\t%s\n", store[index]);
                        } else {
                            System.out.println("\tOpps! The index is out of bound.");
                        }
                    } catch (final NumberFormatException e) {
                        System.out.println("\tOpps! Please input a number.");
                    }
                } else {
                    System.out.println("\tPlease follow this format \"done <index>\".");
                }
            } else { // add task to list
                if (counter >= 100) {
                    System.out.println("\tSorry. The database is full!");
                } else if (tokens[0].equals("todo")) {
                    if (tokens.length < 2) {
                        System.out.println("\tPlease follow this format \"todo <task>\".");
                    } else {
                        store[counter] = new ToDo(input.split(" ", 2)[1]);
                        isInsert = true;
                    }
                } else if (tokens[0].equals("deadline")) {
                    final int index = input.indexOf(" /by ");
                    final String[] splitOnBy = input.split(" /by ", 2);

                    if (index == -1 || splitOnBy.length < 2) {
                        System.out.println("\tPlease follow this format \"deadline <todo> /by <datetime>\".");
                    } else {
                        final String task = splitOnBy[0].split("deadline ", 2)[1];
                        final String datetime = splitOnBy[1];
                        store[counter] = new Deadline(task, datetime);
                        isInsert = true;
                    }
                } else if (tokens[0].equals("event")) {
                    final int index = input.indexOf(" /at ");
                    final String[] splitOnAt = input.split(" /at ", 2);

                    if (index == -1 || splitOnAt.length < 2) {
                        System.out.println("\tPlease follow this format \"event <todo> /at <datetime>\".");
                    } else {
                        final String task = splitOnAt[0].split("event ", 2)[1];
                        final String datetime = splitOnAt[1];
                        store[counter] = new Event(task, datetime);
                        isInsert = true;
                    }
                } else {
                    System.out.println("\tOpps! Try inputting \"todo|deadline|event <task> (</by|/at> <datetime>)\".");
                }

                if (isInsert) {
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.printf("\tTask added: %s\n", store[counter++]);
                    System.out.printf("\tNow you have %d task%s in the list.\n", counter, counter == 1 ? "" : "s");
                }

            }
            horizontalLine();
        }
        scan.close();
    }

    private static void horizontalLine() {
        System.out.println("────────────────────────────────────────────────────────────────────");
    }
}
