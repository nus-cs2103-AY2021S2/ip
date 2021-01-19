import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private PrintStream printer;
    private Scanner input;
    private ArrayList<Task> tasks;

    public static void main(String[] args) {
        Duke d = new Duke(System.out, System.in);
        d.displayGreeting();
        d.loop();
        d.displayFarewell();
    }

    public Duke(PrintStream printer, InputStream inputStream) {
        this.printer = printer;
        this.input = new Scanner(inputStream);
        tasks = new ArrayList<>();
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
        String cmd;

        while (true) {
            cmd = input.nextLine();
            if (cmd.toLowerCase().equals(exitCmd)) {
                break;
            } else if (cmd.toLowerCase().equals(listCmd)) {
                for (int i = 0; i < tasks.size(); i++) {
                    String desc = tasks.get(i).getDescription();
                    printer.println(String.format("%d. %s", i + 1, desc));
                }
            } else {
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
