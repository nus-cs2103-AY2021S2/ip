package duke.duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Handles the main program logic of the Duke task manager program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for a new Duke instance. Takes as a single parameter, <code>filePath</code>, which determines the
     * location from which tasks will be read from or saved to hard disk
     *
     * @param filePath relative file path of <code>.txt</code> file where tasks will be saved/loaded from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public Response getWelcomeMessage() {
        return new Response(ui.displayWelcomeMessage(), ResponseStatus.OK);
    }

    public Response getResponse(String inputCommand) {
        Command command = this.parser.parse(inputCommand);
        Response response = command.execute(tasks);
        try {
            this.storage.save(this.tasks.getTaskList());
        } catch (DukeException e) {
            ui.showSavingError();
        }
        if (response.getStatus() == ResponseStatus.EXIT) {
            Platform.exit();
        }
        return response;
    }
}
