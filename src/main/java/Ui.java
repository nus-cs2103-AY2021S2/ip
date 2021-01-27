import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    public Ui() { }

    public String readCommand() {
        return sc.nextLine();
    }

    //user output at start
    public void printStart() {
        System.out.println(line);
        String logo =
                "      _ __ ___   ___  _   _ _ __ ___   ___  _   _ \n" +
                        "     | '_ ` _ \\ / _ \\| | | | '_ ` _ \\ / _ \\| | | |\n" +
                        "     | | | | | | (_) | |_| | | | | | | (_) | |_| |\n" +
                        "     |_| |_| |_|\\___/ \\__,_|_| |_| |_|\\___/ \\__,_|";
        System.out.println(logo + " is back!\n     What have you awoken MouMou for?");
        System.out.println(line);
    }

    //output at the end
    public void printEnd() {
        System.out.println(line);
        System.out.println(Aligner.align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    public static void printLine() {
        System.out.println(line);
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public void showError(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
