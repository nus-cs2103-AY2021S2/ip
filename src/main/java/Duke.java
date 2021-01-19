import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

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
        final String listCmd = "list";
        final String doneCmd = "done";
        String cmd;

        while (true) {
            cmd = input.nextLine();
            if (cmd.toLowerCase().equals(exitCmd)) {
                // Exit command
                break;
            } else if (cmd.toLowerCase().equals(listCmd)) {
                // List command
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    String desc = t.getDescription();
                    int index = i + 1;
                    String icon = t.getStatusIcon();
                    printer.println(String.format("%d.[%s] %s", index, icon, desc));
                }
            } else if (cmd.toLowerCase().startsWith(doneCmd)) {
                // Done command
                String[] tokens = cmd.split(" ");
                if (tokens.length > 1) {
                    String argStr = tokens[1];
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
                            String icon = t.getStatusIcon();
                            String desc = t.getDescription();
                            printer.println("Nice! I've marked this task as done:");
                            printer.println(String.format("[%s] %s", icon, desc));
                        }
                    } catch (NumberFormatException nfe) {
                        // Argument of wrong type
                        printer.println(String.format("Illegal argument: '%s'. Expected integer", argStr));
                    }
                }
            } else {
                // Add command (default)
                printer.println(String.format("added: %s", cmd));
                Task task = new Task(cmd);
                tasks.add(task);
            }
        }
    }


    public void displayFarewell() {
        printer.println("Goodbye, cruel world!");
    }
}
