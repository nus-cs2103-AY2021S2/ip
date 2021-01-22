import java.util.Scanner;

public class Ui {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Greets the user upon program launch.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public static void showDivider() {
        System.out.println("------------------------------------");
    }

    public static void showSuccess() {

    }

    public static void showError() {

    }

    public static String readCommand() {
        return sc.nextLine();
    }
}
