package duke;

import duke.command.Command;

/**
 * Class representing chat bot, Duke.
 */
public class Duke {
    private static TaskList tasks;
    private static TaskStorage storage;
    private static Ui ui;
    private static boolean isActive;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new TaskStorage("data/tasks.txt");
        tasks = storage.retrieveData();
        isActive = true;
    }

    /**
     * Runs the chat bot.
     * Reads user input and parses it.
     * Exits when user enters "bye".
     */
    private void run() {
        ui.print("POWERED BY JARVIS\n\n\t  Hello! I'm Jarvis.\n\t  How may I help you?");
        while (isActive) {
            String userInput = ui.readInput();
            if (userInput.isBlank()) {
                continue;
            }
            Command cmd = Parser.parse(userInput, tasks);
            if (cmd != null) {
                isActive = cmd.execute(tasks, ui, storage);
            }
        }
    }

    /**
     * Creates a Duke object and runs the program.
     */

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
