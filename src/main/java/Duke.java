import java.time.LocalDate;
import java.util.*;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    private enum Function {
        LIST,
        DONE,
        DELETE,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        NULL
    }

    private static final Map<String, Function> nameToFunction = Map.ofEntries(
            Map.entry("list", Function.LIST),
            Map.entry("done", Function.DONE),
            Map.entry("delete", Function.DELETE),
            Map.entry("bye", Function.BYE),
            Map.entry("todo", Function.TODO),
            Map.entry("deadline", Function.DEADLINE),
            Map.entry("event", Function.EVENT)
    );

    private static void printTasks(Vector<Task> tasks) {
        printLine("Go do work! You need finish these things:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t %d. %s%n", i + 1, tasks.get(i).toString());
        }
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

    private static String getTasksLeftString(Vector<Task> tasks) {
        return String.format("Eh you got %d task%s in total leh", tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    private static void postAddTaskSummary(Vector<Task> tasks) {
        printLine("Okay I remember for you liao:");
        printLine("\t" + tasks.lastElement());
        printLine(getTasksLeftString(tasks));
    }

    private static Task createTask(Function type, String details) throws NoTaskDescriptionException, IncompleteDetailException {
        if (details.isBlank())
            throw new NoTaskDescriptionException();

        Task ret;
        if (type == Function.TODO) {
            ret = new ToDo(details.trim());
        } else {
            boolean isDeadline = type == Function.DEADLINE;
            String sep = isDeadline ? "/by" : "/at";
            int i = details.indexOf(" " + sep);
            if (i == -1)
                throw new IncompleteDetailException(sep);

            String description = details.substring(0, i).trim();
            if (description.isEmpty() || i == 0)
                throw new IncompleteDetailException((sep.equals("/by") ? "deadline" : "event") + " description");

            String dateTime = details.substring(i + sep.length() + 1).trim();
            if (dateTime.isEmpty())
                throw new IncompleteDetailException("dateTime");

            ret = isDeadline ? new Deadline(description, dateTime) : new EventTask(description, dateTime);
        }

        return ret;
    }

    private static boolean processInput(Vector<Task> tasks, Function func, String details)
            throws NoTasksException,
                   InvalidTaskIndexException,
                   NoTaskDescriptionException,
                   IncompleteDetailException,
                   InvalidCommandException {
        boolean active = true;
        switch (func) {
            case LIST:  // list all stored tasks
                if (tasks.isEmpty())
                    throw new NoTasksException();
                else
                    printTasks(tasks);
                break;
            case DONE:  // mark a single task as done
                try {
                    int index = Integer.parseInt(details.trim()) - 1;
                    Task currentTask = tasks.get(index);
                    currentTask.markAsDone();
                    printLine("Good job! The task below is marked done!");
                    printLine(currentTask.toString());
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskIndexException();
                }
                break;
            case DELETE:
                try {
                    int index = Integer.parseInt(details.trim()) - 1;
                    Task selectedTask = tasks.get(index);
                    tasks.remove(index);
                    printLine("Poof! This task is gone:");
                    printLine("\t" + selectedTask.toString());
                    printLine(getTasksLeftString(tasks));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskIndexException();
                }
                break;
            case BYE:
                printLine("Bye bye. Anything call me ah!");
                active = false;
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                tasks.add(createTask(func, details));
                postAddTaskSummary(tasks);
                break;
            default:
                throw new InvalidCommandException();
        }
        return active;
    }

    public static void main(String[] args) {
        // Initial greeting
        System.out.println(HORIZONTAL_LINE);
        printLine("Yo! I'm Ekud!");
        printLine("What you want?");
        System.out.println(HORIZONTAL_LINE);

        // store whatever is given until bye is detected
        Scanner input = new Scanner(System.in);
        Vector<Task> tasks = new Vector<>();
        boolean active = true;

        // process input and act accordingly until bye is encountered
        do {
            String firstWord = input.next();
            Function function = nameToFunction.getOrDefault(firstWord, Function.NULL);
            String details = input.nextLine();
            System.out.println(HORIZONTAL_LINE);
            try {
                active = processInput(tasks, function, details);
            } catch (DukeException e) {
                printLine(e.toString());
            }
            System.out.println(HORIZONTAL_LINE);
        } while (active);
    }
}
