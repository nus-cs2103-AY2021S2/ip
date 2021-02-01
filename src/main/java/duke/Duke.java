package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.logging.Parser;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * A personal chat bot that helps a person keep track of various things.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui;

    /**
     * Constructs a Duke chat bot.
     * @param filePath The filepath where the data are going to be stored at.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Run the Duke chat bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome(tasks);
        while (sc.hasNext()) {
            try {
                String[] fullCommand = ui.readCommand(sc);
                ui.printLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
