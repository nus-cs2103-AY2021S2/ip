import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
//    private Parser parser;
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String PADDING = "\t";
    private static final String WELCOME_MESSAGE = "Yo! I'm Ekud!\nWhat you want?";
    private final Scanner input;
    private final PrintStream output;

    public Ui(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    public Ui() {
        this(System.in, System.out);
    }

    public void showLine() {
        output.println(PADDING + HORIZONTAL_LINE);
    }

    public void printLines(String... lines) {
        for (String line : lines) {
            output.println(PADDING + " " + line);
        }
    }

    public void showWelcome() {
        showLine();
        printLines(WELCOME_MESSAGE.split("\n"));
        showLine();
    }

    public String readCommand() {
        String fullCommand = input.nextLine();
        while (fullCommand.isBlank()) {
            fullCommand = input.next();
        }
        return fullCommand;
    }

    public void showError(String errorMessage) {
        printLines(errorMessage);
    }

    public void showLoadingError() {
        printLines("Error while loading saved tasks",
                "Starting from scratch instead...");
    }
}
