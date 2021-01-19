import java.util.Scanner;

public class DukeBot {
    private final Scanner scanner;

    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printOut(String msg) {
        System.out.println(msg);
        System.out.println(Messages.SEPERATOR);
    }

    public void run() {
        printOut(Messages.WELCOME_MESSGAGE);
        printOut(Messages.GOODBYE);
    }
}
