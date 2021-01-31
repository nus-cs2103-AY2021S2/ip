import pason.commands.Command;
import pason.exceptions.PasonException;
import pason.parser.Parser;
import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class Pason {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Main Pason method.
     */
    public Pason() {
        storage = new Storage();
        ui = new Ui(System.in);
        try {
            tasks = new TaskList(storage);
        } catch (PasonException e) {
            ui.printMessage(e.getMessage());
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }

    }

    /**
     * Runs greeting message and accepts input.
     */
    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommand = ui.readCommand();
                Command command = Parser.parseCommand(inputCommand);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (PasonException e) {
                ui.printMessage(e.getMessage());
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Pason().run();
    }
}
