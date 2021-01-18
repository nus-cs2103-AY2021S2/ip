import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    /**
     * Wrap line(s) with horizontal lines and indent using tab
     *
     * @param lines: array of strings
     */
    private static void wrappedPrint(String[] lines) {
        System.out.println(HORIZONTAL_LINE);
        for (String l : lines) {
            printLine(l);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void wrappedPrint(Vector<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        printLine("Go do work! You need finish these things:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t %d. %s%n", i + 1, tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Wrap single line with horizontal lines and indent using tab
     */
    private static void wrappedPrint(String line) {
        wrappedPrint(new String[]{line});
    }

    private static void printLine(String line) {
        System.out.println("\t " + line);
    }

    private static void processDescription(String full, String sep, String[] descriptions) throws IncompleteDetailException {
        int i = full.indexOf(" " + sep);
        if (i == -1)
            throw new IncompleteDetailException(sep);

        descriptions[0] = full.substring(0, i).trim();
        if (descriptions[0].isEmpty() || i == 0)
            throw new IncompleteDetailException((sep.equals("/by") ? "deadline" : "event") + " description");

        descriptions[1] = full.substring(i + sep.length() + 1).trim();
        if (descriptions[1].isEmpty())
            throw new IncompleteDetailException("time");
    }

    private static void postAddTaskSummary(Vector<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        printLine("Okay I remember for you liao:");
        printLine("\t" + tasks.lastElement());
        printLine(String.format("Eh you got %d task%s in total leh", tasks.size(), tasks.size() == 1 ? "" : "s"));
        System.out.println(HORIZONTAL_LINE);
    }

    private static boolean processInput(Vector<Task> tasks, String firstWord, String details)
            throws NoTasksException,
                   InvalidTaskIndexException,
                   NoTaskDescriptionException,
                   IncompleteDetailException,
                   InvalidCommandException {
        boolean active = true;
        switch (firstWord) {
            case "list":  // list all stored tasks
                if (tasks.isEmpty())
                    throw new NoTasksException();
                else
                    wrappedPrint(tasks);
                break;
            case "done":  // mark a single task as done
                try {
                    int index = Integer.parseInt(details.trim()) - 1;
                    Task currentTask = tasks.get(index);
                    currentTask.markAsDone();
                    wrappedPrint(new String[]{"Good job! The task below is marked done!", currentTask.toString()});
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskIndexException();
                }
                break;
            case "bye":
                wrappedPrint("Bye bye. Anything call me ah!");
                active = false;
                break;
            case "todo":
                if (details.isBlank())
                    throw new NoTaskDescriptionException();
                else {
                    tasks.add(new ToDo(details.trim()));
                    postAddTaskSummary(tasks);
                }
                break;
            case "deadline": {
                String[] descriptions = new String[2];
                processDescription(details, "/by", descriptions);
                tasks.add(new Deadline(descriptions[0], descriptions[1]));
                postAddTaskSummary(tasks);
                break;
            }
            case "event": {
                String[] descriptions = new String[2];
                processDescription(details, "/at", descriptions);
                tasks.add(new EventTask(descriptions[0], descriptions[1]));
                postAddTaskSummary(tasks);
                break;
            }
            default:
                throw new InvalidCommandException();
        }
        return active;
    }

    public static void main(String[] args) {
        // Initial greeting
        wrappedPrint(new String[]{
                "Yo! I'm Ekud!",
                "What you want?"
        });

        // store whatever is given until bye is detected
        Scanner input = new Scanner(System.in);
        Vector<Task> tasks = new Vector<>();
        boolean active = true;

        // process input and act accordingly until bye is encountered
        do {
            String firstWord = input.next();
            String details = input.nextLine();
            try {
                active = processInput(tasks, firstWord, details);
            } catch (DukeException e) {
                wrappedPrint(e.toString());
            }
        } while (active);
    }
}
