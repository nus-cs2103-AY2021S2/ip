package duke;

import duke.command.Command;

public class Duke {
    private static TaskList tasks;
    private static TaskStorage storage;
    private static Ui ui;
    public static boolean toExit;


    public Duke() {
        ui = new Ui();
        storage = new TaskStorage();
        tasks = storage.retrieveData();
        toExit = false;
    }

    public void run() {
        ui.print("POWERED BY JARVIS\n\n\t  Hello! I'm Jarvis.\n\t  How may I help you?");
        while (!toExit) {
            String userInput = ui.readInput();
            if (userInput.isBlank()) {
                continue;
            }
            Command cmd = Parser.parse(userInput, tasks);
            if (cmd != null) {
                cmd.execute(tasks, ui, storage);
            }
        }
    }

    /**
     * An application that serves as a to-do list.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
