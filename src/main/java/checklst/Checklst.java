package checklst;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import checklst.parser.Parser;
import checklst.storage.Storage;
import checklst.task.TaskList;
import checklst.ui.Ui;

/**
 * The Checklst Class represents the entry point of the Checklst Program.
 */
public class Checklst {

    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final Storage storage = new Storage();

    /**
     * The main function of the Checklst Program.
     * Creates and initializes an instance of Checklst and runs it.
     * @param args CLI Arguments.
     */
    public static void main(String[] args) {
        Checklst checklst = new Checklst();
        checklst.run();
    }

    /**
     * Main function to run the Checklst Program.
     */
    public void run() {

        TaskList taskList = new TaskList();
        String[] input;

        try {
            String[] pastCommandHistory = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String command: pastCommandHistory) {
                if (command.equals("")) {
                    continue;
                }
                input = command.split(" ", 2);
                this.parser.parseHistoryCommand(input, taskList);
            }
            this.ui.sendOutput("History successfully restored!");
        } catch (InvalidPathException | IOException e) {
            this.ui.sendOutput("No history found... Initializing from blank state!");
        }

        this.ui.sendWelcome();

        input = ui.readCommand();

        while (!input[0].equals("bye")) {
            this.parser.parse(input, ui, taskList, storage);
            input = ui.readCommand();
        }

        this.ui.sendOutput("Bye! Hope to see you again!");

    }

}
