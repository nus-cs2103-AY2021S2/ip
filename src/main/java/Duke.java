import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

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

    private static void printTasks(Vector<Task> tasks, String dateString) throws DukeException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString.trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format, input d/M/yyyy");
        }

        Vector<TaskWithDateTime> toPrint = new Vector<>();
        for (Task task : tasks) {
            if (task instanceof TaskWithDateTime) {
                TaskWithDateTime t = (TaskWithDateTime) task;
                if (t.getDateTime().toLocalDate().equals(date))
                    toPrint.add(t);
            }
        }
        if (toPrint.isEmpty()) {
            printLine("You're free for the day!");
        } else {
            printLine("You have these deadlines/events:");
            for (int i = 0; i < toPrint.size(); i++) {
                System.out.printf("\t %d. %s%n", i + 1, toPrint.get(i).toString());
            }
        }
    }

    private static void printLine(String line) {
        System.out.println("\t " + line);
    }

    private static String getTasksLeftString(Vector<Task> tasks) {
        return String.format("Eh you got %d task%s in total leh", tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    private static void postAddTaskSummary(Vector<Task> tasks) {
        printLine("Okay I remember for you liao:");
        printLine("\t" + tasks.lastElement());
        printLine(getTasksLeftString(tasks));
    }

    private static Task createTask(Function type, String details) throws DukeException {
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

            String dateTimeString = details.substring(i + sep.length() + 1).trim();
            if (dateTimeString.isEmpty())
                throw new IncompleteDetailException("date and time");

            LocalDateTime localDateTime;
            try {
                localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date and time format, input d/M/yyyy HHmm");
            }

            ret = isDeadline ? new Deadline(description, localDateTime) : new EventTask(description, localDateTime);
        }

        return ret;
    }

    private static boolean processInput(Vector<Task> tasks, Function func, String details) throws DukeException {
        boolean active = true;
        switch (func) {
            case LIST:  // list all stored tasks
                if (tasks.isEmpty())
                    throw new NoTasksException();
                else if (details.isBlank())
                    printTasks(tasks);
                else
                    printTasks(tasks, details);
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
