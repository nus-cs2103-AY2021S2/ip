package duke;
import java.util.Scanner;

import helper.DukeException;
import helper.Parser;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import helper.command.Command;


/**
 * Main class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializing duke
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.showError());
            tasks = new TaskList();
        }
        parser = new Parser();
        String greeting = "Hello! I'm Duke\n" + "Please enter list below";
        ui.dukePrint(greeting);
    }

    /**
     * The main
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        new Duke("duke.txt").loop();
    }

    /**
     * The loop to accept commands
     */
    public void loop() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String s = input.nextLine();
                Command c = parser.parse(s);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }
}
