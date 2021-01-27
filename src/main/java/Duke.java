import java.util.ArrayList;
import java.util.Scanner;

/**
 * Todo:
 * - exceptions yet to be handled:
 * - number of tasks > 100
 * - multiple spaces in between tokens
 * - done command
 * - w/o number
 * - number out of range
 * - help command
 * - Task as abstract class with 3 subclasses (T/D/E)
 * - TaskList as a class !!!
 */

public class Duke {

    /**
     * The task list
     */
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        Ui.printLogo();
        Ui.printEmptyLine();
        Ui.printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        Ui.printLine("Sou, watashi desu!");
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        tasks = Storage.readFromFile();
        for (; ; ) {
            try {
                if (!Parser.processCommand(sc.nextLine(), tasks)) {
                    break;
                }
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
    }
}
