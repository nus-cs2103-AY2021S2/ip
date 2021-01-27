package tlylt.haha;
import java.util.List;
import java.util.Scanner;

public class Haha {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final TaskList database = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();

    /**
     * Enters the program execution.
     */
    public static void main(String[] args) {
        Haha haha = new Haha();
        List<String> list = haha.storage.getTasks();
        haha.database.readTasks(list);
        haha.run();
    }

    /**
     * Drives the program to handle user interaction.
     */
    public void run() {
        ui.welcome();
        // Start while loop
        boolean end = false;
        while (!end) {
            try {
                LegitCommand command = Parser.parseInput(SCANNER.nextLine());
                end = database.executeCommand(command, ui);
                ui.lineBreak();
            } catch (HahaException ex) {
                System.out.println(ex);
                ui.lineBreak();
            }
        }
    }
}
