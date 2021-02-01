package duke;

import java.util.Scanner;

import duke.system.Parser;
import duke.system.Storage;
import duke.system.Ui;
import duke.system.exception.DukeException;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * initiate UI and try to load in the data if exist, else initiate a new list and show error
     * @param filePath the path where the stored txt is saved
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The method to be called in <code>void main()</code> that
     * keep taking in commands until "bye" is entered
     */
    public void run() {
        Parser in = new Parser();
        Scanner sc = new Scanner(System.in);
        ui.showLine();
        ui.showText("Hello! I'm Duke \nWhat can I do for you? \n");
        ui.showLine();
        while (!in.getCommand().equals("bye")) {
            in = new Parser(sc.nextLine());
            ui.showText(in.print(tasks));
            try {
                storage.writeData(tasks.toString());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
