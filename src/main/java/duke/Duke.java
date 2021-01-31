package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke/tasks.txt";

    private static void run() {
        Ui ui = new Ui();
        ui.introduction();
        Storage storage = new Storage(FILE_PATH, ui);
        TaskList taskList = storage.loadFromFile();

        Scanner scannerInput = new Scanner(System.in);
        ui.showMsg("What can I do for you?");

        boolean isExit = false;

        while (!isExit) {
            try {
                String input = scannerInput.nextLine();
                Parser p = new Parser(taskList, ui, storage);
                Command c = p.parse(input);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scannerInput.close();
    }

    public static void main(String[] args) {
        run();
    }
}
