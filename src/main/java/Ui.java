import java.util.Scanner;

public class Ui {
    private static final String BORDER = "-------------------------------------";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(Ui.BORDER);
        System.out.println(message);
        System.out.println(Ui.BORDER);
    }

    public void displayWelcomeMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.displayMessage("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public void showLoadingError() {
        this.displayMessage("An error was encountered when loading from the provided filepath.");
    }

    public void showSavingError() {
        this.displayMessage("An error was encountered when saving to the provided filepath.");
    }

    public String getUserCommand() {
        return this.scanner.nextLine().strip();
    }

    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }
}
