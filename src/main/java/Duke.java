import java.util.Scanner;

public class Duke {

    private static final String FILE_DIR = "duke.txt";

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private void run() {
        storage = new Storage(FILE_DIR);
        ui = new Ui();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        ui.greet();

        Scanner scanner = new Scanner(System.in);
        boolean continueDuke = true;

        while (continueDuke) {
            String input = scanner.nextLine().trim();
            continueDuke = processInput(input);
        }
    }

    private boolean processInput(String input) {
        Command command;
        try {
            command = CommandParser.parse(input);
            command.addTaskList(taskList);
            String result = command.execute();
            ui.print(result);

            if (command.shouldSave()) {
                storage.save(taskList);
            }

            if (command.shouldExit()) {
                return false;
            }

        } catch (IllegalArgumentException e) {
            ui.print("☹ Sorry, please enter a valid command.\n\tCommands available:\n\t\t- list\n\t\t" +
                    "- done [task number]\n\t\t- todo [description]\n\t\t- deadline [description] /by [deadline]\n\t\t" +
                    "- event [description] /at [datetime]\n\t\t- delete [task number]\n\t\t- bye");
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        return true;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
