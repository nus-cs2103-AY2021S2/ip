package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Handles the main program logic of the Duke task manager program
 */
public class Duke extends Application {

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

    public String getWelcomeMessage() {
        return ui.displayWelcomeMessage();
    }

    public Response getResponse(String inputCommand) {
        Response response = this.parser.parse(inputCommand);
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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
