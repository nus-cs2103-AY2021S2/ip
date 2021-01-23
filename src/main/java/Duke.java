import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    /**
     * Lifecycle of the chatbot
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskManager taskManager = new TaskManager();
        Command.setup(ui, taskManager);

        try {
            Storage.loadTasksTo(taskManager);
        } catch (DukeLoadException e) {
            ui.printError(e.getMessage());
        }

        // Opening message
        ui.printWelcomeMsg(CHATBOT_NAME);

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String line = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            line = scanner.nextLine();

            try {
                Command command = Parser.parse(line);
                command.execute();
                isActive = !command.isToExit();
            } catch(DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        // Closing message
        ui.printGoodbyeMsg();
    }
}
