import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private final String FILE_PATH = System.getProperty("user.dir") + "/duke.txt";
    private final Database dukeDataBase = new Database(FILE_PATH);

    Duke() {}

    public static void main(String[] args) {
        Duke dukeBot = new Duke();
        dukeBot.run();
    }

    public void run() {
        Printer.printGreeting();
        dukeDataBase.syncFromFile(tasks);
        Processor processor = new Processor(tasks, dukeDataBase);

        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver) {
            String input = sc.nextLine();
            Printer.printLine();
            isOver = processor.processSentence(input);
            Printer.printLine();
        }
    }
}
