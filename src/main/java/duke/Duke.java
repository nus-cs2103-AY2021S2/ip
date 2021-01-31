package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeLoadException;

/** A chat bot that can help the user manage their tasks */
public class Duke {
    /** Name of the chat bot */
    private static final String CHATBOT_NAME = "Mantaro";
    /** Determine whether the chat bot continue to run */
    private boolean isActive;
    /** Manages a task list in the chat bot */
    private TaskManager taskManager;
    /** Provide different way of printing messages for the chat bot */
    private Ui ui;

    /** Constructor of Duke */
    public Duke() {
        isActive = true;

        ui = new Ui();
        taskManager = new TaskManager();
        Command.setup(ui, taskManager);

        try {
            Storage.loadTasksTo(taskManager);
        } catch (DukeLoadException e) {
            ui.printError(e.getMessage());
        }
    }

    /** Lifecycle of Duke */
    public void run() {
        ui.printWelcomeMsg(CHATBOT_NAME);

        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (isActive) {
            line = scanner.nextLine();
            try {
                Command command = Parser.parse(line);
                command.execute();
                isActive = !command.willExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        ui.printGoodbyeMsg();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

//    /**
//     * Lifecycle of the program
//     *
//     * @param args Command line arguments
//     */
//    public static void main(String[] args) {
//        Duke bot = new Duke();
//        bot.run();
//    }
}
