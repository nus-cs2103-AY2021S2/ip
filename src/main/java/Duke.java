import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private final PrintStream printer;
    private final Scanner input;
    private final ArrayList<Task> tasks;

    public Duke(PrintStream printer, InputStream inputStream) {
        this.printer = printer;
        this.input = new Scanner(inputStream);
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke d = new Duke(System.out, System.in);
        d.displayGreeting();
        d.loop();
        d.displayFarewell();
    }

    public void displayGreeting() {
        final String logo = "███      ███                             ███                      \n"
                + " ███    ███   ██                         ███                      \n"
                + "  ███ ███        ███ ███    █████        ███     ███     ███ ███  \n"
                + "    ███      ███  ███  ███ ███  ███      ███   ██   ███   ███  ███\n"
                + "    ███      ███  ███  ███ ██   ███      ███  █████████   ███  ███\n"
                + "    ███      ███  ███  ███ ████ ███ ██   ███  ██          ███  ███\n"
                + "    ███      ███ ████  ███     ███   █████      █████    ████  ███\n"
                + "                             ████                                 \n";
        printer.println("Hi there. I am");
        printer.println(logo);
        printer.println("How may I help you?");
    }

    public void loop() {
        final String exitCmd = "bye";

        while (true) {
            String cmd = input.nextLine();

            if (cmd.equalsIgnoreCase(exitCmd)) {
                // Exit command
                break;
            } else {
                /*
                Try each registered command one by one until one is found or all are tried.
                A loop cannot be used because the methods throw exceptions
                 */
                try {
                    if (isAddToDoCmd(cmd)) {
                        handleAddToDoCmd(cmd);
                    } else if (isAddDeadlineCmd(cmd)) {
                        handleAddDeadlineCmd(cmd);
                    } else if (isAddEventCmd(cmd)) {
                        handleAddEventCmd(cmd);
                    } else if (isListCmd(cmd)) {
                        handleListCmd(cmd);
                    } else if (isDoneCmd(cmd)) {
                        handleDoneCmd(cmd);
                    } else if (isDeleteCmd(cmd)) {
                        handleDeleteCmd(cmd);
                    } else {
                        // default: unrecognized command
                        printer.println(String.format("Sorry, I don't know what '%s' means", cmd));
                    }

                } catch (DukeException ex) {
                    printer.println(ex.getMessage());
                }
            }
        }
    }

    public void displayFarewell() {
        printer.println("Goodbye, cruel world!");
    }

    private boolean isAddToDoCmd(String cmd) {
        Pattern toDoCmd = Pattern.compile("(?i)todo\\b");
        Matcher cmdMatcher = toDoCmd.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleAddToDoCmd(String cmd) throws DukeException {
        Pattern toDoPattern = Pattern.compile("(?i)todo\\s+(\\w.*)");
        Matcher toDoMatcher = toDoPattern.matcher(cmd);

        if (toDoMatcher.find()) {
            String taskDesc = toDoMatcher.group(1);

            ToDo toDo = new ToDo(taskDesc);
            tasks.add(toDo);

            printAddTaskAck(toDo);
        } else {
            // Matched command but invalid argument
            throw new DukeException("The description of a todo cannot be empty!\n"
                    + "Expected format: todo <DESCRIPTION>");
        }
    }

    private boolean isAddDeadlineCmd(String cmd) {
        Pattern deadlineCmd = Pattern.compile("(?i)deadline\\b");
        Matcher cmdMatcher = deadlineCmd.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleAddDeadlineCmd(String cmd) throws DukeException {
        Pattern descPattern = Pattern.compile("(?i)deadline\\s+(\\w.*)");
        Matcher descMatcher = descPattern.matcher(cmd);
        if (!descMatcher.find()) {
            throw new DukeException("The description of a deadline cannot be empty!\n"
                    + "Expected format: deadline <DESCRIPTION> /by <TIME>");
        }

        Pattern timePattern = Pattern.compile("(?i)deadline\\s+(\\w.*)\\s+\\/by\\s+(\\w.*)");
        Matcher timeMatcher = timePattern.matcher(cmd);

        if (!timeMatcher.find()) {
            throw new DukeException("A deadline must have a time!\n"
                    + "Expected format: deadline <DESCRIPTION> /by <TIME>");
        }

        String taskDesc = timeMatcher.group(1);
        String time = timeMatcher.group(2);

        Deadline deadline = new Deadline(taskDesc, time);
        tasks.add(deadline);

        printAddTaskAck(deadline);
    }

    private boolean isAddEventCmd(String cmd) {
        Pattern eventCmd = Pattern.compile("(?i)event\\b");
        Matcher cmdMatcher = eventCmd.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleAddEventCmd(String cmd) throws DukeException {
        Pattern descPattern = Pattern.compile("(?i)event\\s+(\\w.*)");
        Matcher descMatcher = descPattern.matcher(cmd);
        if (!descMatcher.find()) {
            throw new DukeException("The description of an event cannot be empty!\n"
                    + "Expected format: event <DESCRIPTION> /at <PERIOD>");
        }

        Pattern periodPattern = Pattern.compile("(?i)event\\s+(\\w.*)\\s+\\/at\\s+(\\w.*)");
        Matcher periodMatcher = periodPattern.matcher(cmd);

        if (!periodMatcher.find()) {
            throw new DukeException("An event must have a period!\n"
                    + "Expected format: event <DESCRIPTION> /at <PERIOD>");
        }

        String taskDesc = periodMatcher.group(1);
        String period = periodMatcher.group(2);

        Event event = new Event(taskDesc, period);
        tasks.add(event);

        printAddTaskAck(event);
    }

    private void printAddTaskAck(Task task) {
        printer.println("Got it. I've added this task:");
        printer.println(String.format("%s", task.toString()));
        printer.println(String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    private boolean isListCmd(String cmd) {
        Pattern listCmd = Pattern.compile("(?i)list\\b");
        Matcher cmdMatcher = listCmd.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleListCmd(String cmd) throws DukeException {
        Pattern listPattern = Pattern.compile("(?i)list(\\s*)$");
        Matcher listMatcher = listPattern.matcher(cmd);
        if (!listMatcher.find()) {
            throw new DukeException("Please do not include any arguments after the list command.\n"
                    + "Expected format: list");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            int index = i + 1;
            printer.println(String.format("%d.%s", index, t));
        }
    }

    private boolean isDoneCmd(String cmd) {
        Pattern donePattern = Pattern.compile("(?i)done\\b");
        Matcher cmdMatcher = donePattern.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleDoneCmd(String cmd) throws DukeException {
        Pattern donePattern = Pattern.compile("(?i)done\\s+(\\d+)$");
        Matcher doneMatcher = donePattern.matcher(cmd);
        if (!doneMatcher.find()) {
            throw new DukeException(String.format("A done command must specify a task number.\n"
                    + "Valid task numbers are 1 to %d.\n"
                    + "Expected format: done <TASK NUMBER>", tasks.size()));
        }

        String argStr = doneMatcher.group(1);
        try {
            int arg = Integer.parseInt(argStr);
            if (arg < 1 || arg > tasks.size()) {
                // Argument out of range
                throw new DukeException(String.format("Task %d does not exist!\n"
                        + "Valid task numbers are 1 to %d.", arg, tasks.size()));
            } else {
                // Valid argument in range
                int index = arg - 1;
                Task t = tasks.get(index);
                t.markAsDone();
                printer.println("Nice! I've marked this task as done:");
                printer.println(String.format("%s", t.toString()));
            }
        } catch (NumberFormatException nfe) {
            // Argument of wrong type
            throw new DukeException(String.format("Illegal argument: '%s'. Expected integer.\n"
                    + "Valid task numbers are 1 to %d.", argStr, tasks.size()));
        }
    }

    private boolean isDeleteCmd(String cmd) {
        Pattern deletePattern = Pattern.compile("(?i)delete\\b");
        Matcher cmdMatcher = deletePattern.matcher(cmd);
        return cmdMatcher.find();
    }

    private void handleDeleteCmd(String cmd) throws DukeException {
        Pattern deletePattern = Pattern.compile("(?i)delete\\s+(\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(cmd);
        if (!deleteMatcher.find()) {
            throw new DukeException(String.format("A delete command must specify a task number.\n"
                    + "Valid task numbers are 1 to %d.\n"
                    + "Expected format: delete <TASK NUMBER>", tasks.size()));
        }

        String argStr = deleteMatcher.group(1);
        try {
            int arg = Integer.parseInt(argStr);
            if (arg < 1 || arg > tasks.size()) {
                // Argument out of range
                throw new DukeException(String.format("Task %d does not exist!\n"
                        + "Valid task numbers are 1 to %d.", arg, tasks.size()));
            } else {
                // Valid argument in range
                int index = arg - 1;
                Task t = tasks.get(index);
                tasks.remove(index);
                printer.println("Noted. I've removed this task:");
                printer.println(String.format("%s", t.toString()));
                printer.println(String.format("Now you have %d task(s) in the list.", tasks.size()));
            }
        } catch (NumberFormatException nfe) {
            // Argument of wrong type
            throw new DukeException(String.format("Illegal argument: '%s'. Expected integer.\n"
                    + "Valid task numbers are 1 to %d.", argStr, tasks.size()));
        }
    }
}
