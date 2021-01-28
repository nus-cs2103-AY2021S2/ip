package duke;

import duke.command.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * main class containing the Duke Chatbot main logic.
 */

public class Duke {
    private static Storage storage;
    private Ui ui;
    TaskList tasks;

    Duke() {
        this.storage = initializeStorage();
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadStorage());
        } catch ( DukeException err) {
            System.out.println(err.getMessage());
            tasks = new TaskList();
        }
    }

    private Storage initializeStorage() {
        File directory = new File("data"); // Check if directory exists.
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Storage("data/duke.txt");
    }


    public void run() {
        ui.displayWelcomeMessage();
        boolean shouldExit = false; // set to false is a command fails to execute exit command
        while (!shouldExit) {
            try {
                String input = ui.getUserCommand();
                Parser parser = new Parser(input);
                Command command = parser.parseCommand();
                command.execute(ui, tasks, storage);
                shouldExit = command.shouldExit();
            } catch (DukeException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke chatbot  = new Duke();
        chatbot.run();
    }
}
