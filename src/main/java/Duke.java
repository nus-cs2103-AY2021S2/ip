import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Duke {
    private PrintStream printer;
    private Scanner input;

    public static void main(String[] args) {
        Duke d = new Duke(System.out, System.in);
        d.displayGreeting();
        d.loop();
        d.displayFarewell();
    }

    public Duke(PrintStream printer, InputStream inputStream) {
        this.printer = printer;
        this.input = new Scanner(inputStream);
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
        String cmd;
        boolean isDone;
        while (true) {
            cmd = input.nextLine();
            if (cmd.toLowerCase().equals(exitCmd)) {
                break;
            }
            printer.println(cmd);
        }
    }

    public void displayFarewell() {
        printer.println("Goodbye, cruel world!");
    }
}
