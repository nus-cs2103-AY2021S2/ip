package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;

/**
 * Encapsulates Duke, the chatbot application.
 *
 * @author Aaron Saw Min Sern
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Sole constructor for class Duke.
     *
     * @param filePath the file path location at which Duke stores data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded = storage.loadFile();
        if (loaded == null) {
            tasks = new TaskList();
            storage.createDirectoryAndFile();
        } else {
            tasks = loaded;
        }
    }

    /**
     * Runs the program. This method is the starting point of the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        final Scanner scan = new Scanner(System.in);
        while (!isExit && scan.hasNextLine()) {
            final String input = scan.nextLine().strip();
            ui.showLine();

            if (input.equals("")) {
                System.out.println("\t...");
            } else {
                try {
                    final Command command = Parser.parseCommand(input);
                    command.execute(tasks, ui, storage);
                    isExit = command.isExit();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ui.showLine();
        }
        scan.close();

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the program with the file path location of "./data/task.txt".
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
