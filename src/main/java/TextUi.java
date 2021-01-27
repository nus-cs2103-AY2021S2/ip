import java.io.PrintStream;
import java.util.Scanner;

public class TextUi implements Ui {
    private static final String COMMAND_PROMPT = "Type Command> ";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        in = new Scanner(System.in);
        out = System.out;
    }

    @Override
    public String readCommand() {
        String line;
        do {
            out.print(COMMAND_PROMPT);
            line = in.nextLine();
        } while (isInvalid(line));
        return line;
    }

    @Override
    public void showCommandResult(CommandResult result) {
        if (result.hasFeedback()) {
            out.println(result.getFeedback());
        }
    }

    @Override
    public void showError(String errMsg) {
        out.println(errMsg);
    }

    @Override
    public void showFarewell() {
        out.println("Goodbye, cruel world!");
    }

    @Override
    public void showGreeting() {
        final String logo = "███      ███                             ███                      \n"
                + " ███    ███   ██                         ███                      \n"
                + "  ███ ███        ███ ███    █████        ███     ███     ███ ███  \n"
                + "    ███      ███  ███  ███ ███  ███      ███   ██   ███   ███  ███\n"
                + "    ███      ███  ███  ███ ██   ███      ███  █████████   ███  ███\n"
                + "    ███      ███  ███  ███ ████ ███ ██   ███  ██          ███  ███\n"
                + "    ███      ███ ████  ███     ███   █████      █████    ████  ███\n"
                + "                             ████                                 \n";
        out.println("Hi there. I am");
        out.println(logo);
        out.println("How may I help you?");
    }

    @Override
    public void showMessage(String msg) {
        out.println(msg);
    }

    private boolean isInvalid(String input) {
        return input.isBlank();
    }
}
