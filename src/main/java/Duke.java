import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";

    private boolean isActive;
    private TaskManager taskManager;
    private Ui ui;

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

    public void run() {
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

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
