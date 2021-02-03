import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constuctor for Duke
     */
    public Duke() {
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

    /**
     * Method to get response to an input for GUI Duke Program.
     *
     * @param input input command.
     * @return Response String output.
     * @throws DukeException Different kinds of Duke Exception errors.
     *
     */
    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            if (e.getMessage().equals("IndexOutOfBound")) {
                return ui.arrayIndexOutOfBoundsError();
            }
            if (e.getMessage().equals("EmptyIndex")) {
                return ui.emptyIndexError();
            }
            if (e.getMessage().equals("EmptyDescription")) {
                return ui.emptyDescriptionError(e.getInfo());
            }
            if (e.getMessage().equals("EmptyDetails")) {
                return ui.emptyDetailsError(e.getInfo());
            }
            if (e.getMessage().equals("UnknownCommand")) {
                return ui.unknownCommandError();
            }
        }
        return "Error! Please Try Another Command!";
    }

    /**
     * Runs the program for the Text-Based Duke Program.
     */
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
                if (e.getMessage().equals("UnknownCommand")) {
                    ui.unknownCommandError();
                }
            }
        }
    }
}
