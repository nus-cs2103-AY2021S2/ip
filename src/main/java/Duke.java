import Commands.Command;
import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import Parser.Parser;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.storage = new Storage();

        ui.preload();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            if (e.getMessage().equals("LoadFileError")) {
                ui.loadFileError();
            }
        }
    }

    public void run() {
        ui.introduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                if (e.getMessage().equals("IndexOutOfBound")) {
                    ui.arrayIndexOutOfBoundsError();
                }
                if (e.getMessage().equals("EmptyIndex")) {
                    ui.emptyIndexError();
                }
                if (e.getMessage().equals("EmptyDescription")) {
                    ui.emptyDescriptionError(e.getInfo());
                }
                if (e.getMessage().equals("EmptyDetails")) {
                    ui.emptyDetailsError(e.getInfo());
                }
                if (e.getMessage().equals("UnknownCommand")){
                    ui.unknownCommandError();
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke simulator = new Duke();
        simulator.run();
    }
}
