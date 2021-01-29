package duke;

import duke.command.Command;
import duke.logging.Parser;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    private final Storage storage;
    private TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

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