package duke;

import duke.command.Command;

public class Duke {
    private static TaskList tasks;
    private static TaskStorage storage;
    private static Ui ui;
    private static boolean isActive;


    public Duke() {
        ui = new Ui();
        storage = new TaskStorage("data/tasks.txt");
        tasks = storage.retrieveData();
        isActive = true;
    }

    public void run() {
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
     * An application that serves as a to-do list.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
