import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        horizontalLine();

        final List<Task> store = new ArrayList<>();
        final Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            final String input = scan.nextLine().strip();
            final String command = input.split(" ")[0];
            horizontalLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                horizontalLine();
                break;
            }

            if (input.equals("")) { // empty string
                System.out.println("\t...");
            } else if (input.equals("list")) { // list tasks
                listTasks(store);
            } else if (command.equals("done")) { // mark task as done
                markTaskAsDone(store, input);
            } else if (command.equals("delete")) { // mark task as delete
                deleteTask(store, input);
            } else { // add task to list
                addTaskToList(store, command, input);
            }
            horizontalLine();
        }
        scan.close();
    }

    private static void deleteTask(List<Task> store, String input) {
        final String[] splitOnSpace = input.split(" ", 2);

        if (splitOnSpace.length == 2 && !splitOnSpace[1].strip().equals("")) {
            try {
                final int index = Integer.parseInt(splitOnSpace[1].strip()) - 1;
                if (0 <= index && index < store.size()) {
                    final Task removed = store.remove(index);
                    System.out.println("\tNoted. I've removed this task: ");
                    System.out.printf("\t%s\n", removed);
                    System.out.printf("\tNow you have %d task%s in the list.\n", store.size(), store.size() == 1 ? "" : "s");
                } else {
                    System.out.println("\tOops! The index is out of bound.");
                }
            } catch (final NumberFormatException e) {
                System.out.println("\tOops! Please input a number.");
            }
        } else {
            System.out.println("\tPlease follow this format \"done <index>\".");
        }
	}

	private static void addTaskToList(List<Task> store, String command, String input) {
        boolean isInsert = false;
        if (store.size() >= 100) {
            System.out.println("\tSorry. The database is full!");
        } else if (command.equals("todo")) {
            final String[] splitOnSpace = input.split(" ", 2);

            if (splitOnSpace.length < 2 || splitOnSpace[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"todo <task>\".");
            } else {
                final String task = splitOnSpace[1].strip();
                store.add(new ToDo(task));
                isInsert = true;
            }
        } else if (command.equals("deadline")) {
            final int index = input.indexOf(" /by ");
            final String[] splitOnBy = input.split(" /by ", 2);

            if (index == -1 || splitOnBy.length < 2 || splitOnBy[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"deadline <todo> /by <datetime>\".");
            } else {
                final String task = splitOnBy[0].split("deadline ", 2)[1].strip();
                final String datetime = splitOnBy[1].strip();
                store.add(new Deadline(task, datetime));
                isInsert = true;
            }
        } else if (command.equals("event")) {
            final int index = input.indexOf(" /at ");
            final String[] splitOnAt = input.split(" /at ", 2);

            if (index == -1 || splitOnAt.length < 2 || splitOnAt[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"event <todo> /at <datetime>\".");
            } else {
                final String task = splitOnAt[0].split("event ", 2)[1].strip();
                final String datetime = splitOnAt[1].strip();
                store.add(new Event(task, datetime));
                isInsert = true;
            }
        } else {
            System.out.println("\tOops! Try inputting \"todo|deadline|event <task> (</by|/at> <datetime>)\".");
        }

        if (isInsert) {
            System.out.println("\tGot it. I've added this task: ");
            System.out.printf("\tTask added: %s\n", store.get(store.size() - 1));
            System.out.printf("\tNow you have %d task%s in the list.\n", store.size(), store.size() == 1 ? "" : "s");
        }
    }

    private static void markTaskAsDone(List<Task> store, String input) {
        final String[] splitOnSpace = input.split(" ", 2);

        if (splitOnSpace.length == 2 && !splitOnSpace[1].strip().equals("")) {
            try {
                final int index = Integer.parseInt(splitOnSpace[1].strip()) - 1;
                if (0 <= index && index < store.size()) {
                    store.get(index).markAsDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t%s\n", store.get(index));
                } else {
                    System.out.println("\tOops! The index is out of bound.");
                }
            } catch (final NumberFormatException e) {
                System.out.println("\tOops! Please input a number.");
            }
        } else {
            System.out.println("\tPlease follow this format \"done <index>\".");
        }
    }

    private static void listTasks(List<Task> store) {
        if (store.size() == 0) {
            System.out.println("\tHmm... You do not have any tasks!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        int i = 0;
        for (final Task t : store) {
            System.out.printf("\t%d. %s\n", ++i, t);
        }
    }

    private static void horizontalLine() {
        System.out.println("────────────────────────────────────────────────────────────────────────────────");
    }
}
