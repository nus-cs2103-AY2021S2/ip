import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a personal assistant chat bot that helps users to
 * keep track of various things.
 * @author Damith C. Rajapakse, Wu Weiming
 */
public class Duke {
    Tasks tasks;
    Storage storage;

    public Duke(String filePath) throws IOException {
        this.tasks = new Tasks();
        this.storage = new Storage(filePath);
        storage.retrieveTasks(tasks);
    }

    public static void main(String[] args) {
        try {
            Duke d = new Duke("duke.txt");
            d.run();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void run() throws IOException {
        printHello();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("duke.txt");
        Parser parser = new Parser();

        while (true) {
            String input = sc.nextLine();
            boolean shouldExit = parser.parse(input, tasks);
            if (shouldExit) {
                break;
            }
        }
        sc.close();
        storage.storeTasks(tasks);
    }

    /**
     * Prints a horizontal line to format text.
     */
    public static void formatText() {
        System.out.println("******************************************************");
    }

    public void printHello() {
        String logo = ".------..------..------..------.\n"
                + "|D.--. ||U.--. ||K.--. ||E.--. |\n"
                + "| :/\\: || (\\/) || :/\\: || (\\/) |\n"
                + "| (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'D|| '--'U|| '--'K|| '--'E|\n"
                + "`------'`------'`------'`------'";

        System.out.println("Hello, this is\n" + logo);
    }
}
