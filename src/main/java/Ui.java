import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().strip();
    }

    // format for greeting, echo and exit
    public void printMessage(String message) {
        String newMessage = message.replaceAll("\n", "\n     ");
        System.out.println("    ____________________________________________________________\n" + "     " + newMessage
                + "\n" + "    ____________________________________________________________\n");
    }

    public void showWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        printMessage(welcomeMessage);
    }

    public void showLoadingError() {
        String loadingErrMessage = "OOPS!!! Was unable to load from save\nStarting a new task list...";
        printMessage(loadingErrMessage);
    }

    public void showReadingError() {
        String loadingErrMessage = "OOPS!!! Was unable to parse save file\nStarting a new task list...";
        printMessage(loadingErrMessage);
    }
}
