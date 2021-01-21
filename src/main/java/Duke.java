import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private PrintStream printer;
    private Scanner input;
    private ArrayList<Task> tasks;

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

        List<Predicate<String>> handlers = new ArrayList<>();
        handlers.add(this::tryAddToDo);
        handlers.add(this::tryListCmd);
        handlers.add(this::tryDoneCmd);
        handlers.add(this::tryAddDeadline);
        handlers.add(this::tryAddEvent);

        while (true) {
            String cmd = input.nextLine();

            if (cmd.toLowerCase().equals(exitCmd)) {
                // Exit command
                break;
            } else {
                // Try each registered command one by one until one is found or all are tried
                boolean isCmdFound = false;
                for (Predicate<String> handler : handlers) {
                    if (handler.test(cmd)) {
                        isCmdFound = true;
                        break;
                    }
                }

                if (!isCmdFound) {
                    // default: unrecognized command
                    printer.println(String.format("Unrecognized command: '%s'", cmd));
                }
            }
        }
    }

    public void displayFarewell() {
        printer.println("Goodbye, cruel world!");
    }

    private boolean tryAddToDo(String cmd) {
        Pattern toDoPattern = Pattern.compile("(?i)todo (.+)");
        Matcher m = toDoPattern.matcher(cmd);
        if (m.find()) {
            String taskDesc = m.group(1);

            ToDo toDo = new ToDo(taskDesc);
            tasks.add(toDo);

            printAddTaskAck(toDo);

            return true;
        }

        return false;
    }

    private boolean tryAddDeadline(String cmd) {
        Pattern deadlinePattern = Pattern.compile("(?i)deadline (.+) \\/by (.+)");
        Matcher m = deadlinePattern.matcher(cmd);
        if (m.find()) {
            String taskDesc = m.group(1);
            String time = m.group(2);

            Deadline deadline = new Deadline(taskDesc, time);
            tasks.add(deadline);

            printAddTaskAck(deadline);

            return true;
        }

        return false;
    }

    private boolean tryAddEvent(String cmd) {
        Pattern eventPattern = Pattern.compile("(?i)event (.+) \\/at (.+)");
        Matcher m = eventPattern.matcher(cmd);
        if (m.find()) {
            String taskDesc = m.group(1);
            String period = m.group(2);

            Event event = new Event(taskDesc, period);
            tasks.add(event);

            printAddTaskAck(event);

            return true;
        }

        return false;
    }

    private void printAddTaskAck(Task task) {
        printer.println("Got it. I've added this task:");
        printer.println(String.format("%s", task.toString()));
        printer.println(String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    private boolean tryListCmd(String cmd) {
        Pattern listPattern = Pattern.compile("(?i)list");
        Matcher m = listPattern.matcher(cmd);
        if (m.find()) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                int index = i + 1;
                printer.println(String.format("%d.%s", index, t));
            }
            return true;
        }

        return false;
    }

    private boolean tryDoneCmd(String cmd) {
        Pattern donePattern = Pattern.compile("(?i)done (\\d+)");
        Matcher m = donePattern.matcher(cmd);
        if (m.find()) {
            String argStr = m.group(1);
            try {
                int arg = Integer.parseInt(argStr);
                if (arg < 1 || arg > tasks.size()) {
                    // Argument out of range
                    printer.println(String.format("Task %d does not exist!", arg));
                } else {
                    // Valid argument in range
                    int index = arg - 1;
                    Task t = tasks.get(index);
                    t.markAsDone();
                    printer.println("Nice! I've marked this task as done:");
                    printer.println(String.format("%s", t.toString()));
                }
                return true;
            } catch (NumberFormatException nfe) {
                // Argument of wrong type
                printer.println(String.format("Illegal argument: '%s'. Expected integer", argStr));
                return true;
            }
        }

        return false;
    }
}
