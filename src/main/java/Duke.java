import java.io.IOException;
import java.lang.System;

public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String path) {
        ui = new Ui();
        try {
            storage = new Storage(path);
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            ui.printStorageIoError();
            System.exit(0);
        } catch (DukeException de) {
            ui.printDukeException(de);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntroduction();
        boolean isExit = false;

        while(!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printUpdateIoError(ie);
            }
        }

        ui.closeScanner();
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke("data/savedTasks.txt").run();
    }
}