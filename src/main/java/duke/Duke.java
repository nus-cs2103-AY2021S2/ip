package duke;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.commands.Command;
import duke.ui.CliUi;
import duke.ui.JavafxUi;
import duke.ui.Ui;

/**
 * Main Duke class.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(boolean isCli) {
        this(null, isCli);
    }

    /**
     * Creates new duke instance
     * @param directoryPath path to store data
     * @param isCli specify whether the duke instance is a command line interface
     */
    public Duke(String directoryPath, boolean isCli) {
        if (isCli) {
            ui = new CliUi();
        } else {
            ui = new JavafxUi();
        }
        if (directoryPath != null) {
            storage = new Storage(Paths.get(directoryPath));
        } else {
            storage = new Storage();
        }
        try {
            tasks = storage.readTasks();
        } catch (IOException e) {
            ui.printError("Unable to create file");
            tasks = new TaskList();
        } catch (Exception e) {
            ui.printError("Unable to parse file");
            tasks = new TaskList();
        }
    }


    /**
     * Executes the Duke program by reading in commands and giving appropriate outputs.
     */
    public void run() {
        ui.printIntro();

        Scanner stdin = new Scanner(System.in);
        String line;
        boolean end = false;

        while (stdin.hasNextLine()) {
            line = stdin.nextLine();
            ui.printHorizontalLine();

            getResponse(line);

            ui.printHorizontalLine();

            if (end) {
                break;
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.writeTasks(tasks);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError("Unable to write to file");
        }
        String replyString = ui.getReplyString();
        ui.resetReplyString();
        return replyString;
    }

    public static void main(String[] args) {
        new Duke(true).run();
    }
}
