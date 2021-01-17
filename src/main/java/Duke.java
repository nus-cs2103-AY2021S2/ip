import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    /**
     * Wrap line(s) with horizontal lines and indent using tab
     * @param lines: array of strings
     */
    private static void wrappedPrint(String[] lines) {
        System.out.println(HORIZONTAL_LINE);
        for (String l: lines) {
            System.out.println("\t " + l);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Wrap single line with horizontal lines and indent using tab
     */
    private static void wrappedPrint(String line) {
        wrappedPrint(new String[]{line});
    }

    private static void printHistory(Vector<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.printf("\t %d. %s%n", i + 1, currentTask);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        // Initial greeting
        wrappedPrint(new String[]{"Yo! I'm Ekud!", "What you want?"});

        // store whatever is given until bye is detected
        Scanner input = new Scanner(System.in);
        Vector<Task> tasks = new Vector<>();
        String description;
        boolean active = true;

        do {
            description = input.nextLine();
            if (description.startsWith("done")) {
                int index = Integer.parseInt(description.substring(5)) - 1;
                Task currentTask = tasks.get(index);
                currentTask.markAsDone();
                wrappedPrint(new String[]{"Good job! The task below is marked done!", currentTask.toString()});
                continue;
            }
            switch (description) {
                case "list":
                    printHistory(tasks);
                    break;
                case "bye":
                    wrappedPrint("Bye bye. Anything call me ah!");
                    active = false;
                    break;
                default:
                    tasks.add(new Task(description));
                    wrappedPrint("added: " + tasks.lastElement().description);
            }
        } while (active);
    }
}
