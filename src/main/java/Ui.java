import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showLoadingError() {
        String loadingErrorMessage = "Saved file not found.\n";
        loadingErrorMessage += "Using a new task list.";
        printMessage(loadingErrorMessage);
        return;
    }

    public String readLine() {
        Scanner scan = new Scanner(System.in);
        String taskString = scan.nextLine();
        //scan.close();
        return taskString;
    }

    public void printMessage(String message) {
        String spaces = "     ";
        String horizLine = "    _____________________________________"
                + "__________________________________\n";

        message = spaces + message;
        message = message.replace("\n", "\n" + spaces);

        System.out.println(horizLine);
        System.out.println(message);
        System.out.println(horizLine);
        return;
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from \n" + logo + "\n"
                + "What can I do for you?\n";
        welcomeMessage += "Commands:  list, todo, event, deadline, done, delete, bye";
        printMessage(welcomeMessage);
        return;
    }

    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        printMessage(goodbyeMessage);
        return;
    }

}
