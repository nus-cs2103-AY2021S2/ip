import java.util.Scanner;

/**
 * Driver class for Duke project
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException ex) {
            ui.outputMessage(ex.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean continueInput = true;
        while (continueInput) {
            String input = ui.nextCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                continueInput = command.continueInput();
            }
            catch (DukeException ex) {
                ui.outputMessage(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/data/Duke.txt";
        new Duke(filePath).run();
    }
}