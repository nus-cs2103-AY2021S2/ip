package duke;

import javafx.scene.image.Image;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    String littleMyUrl =
            "https://static.wikia.nocookie.net/moomin/images/0/05/My1.png/revision/latest/top-crop/width/300/height/300?cb=20190914020308";
    String moominUrl =
            "https://i.pinimg.com/originals/19/3a/15/193a1552cc00589da96c9c8ce8cc4ba9.png";
    private Image user = new Image(littleMyUrl, 160, 60, false, true);
    private Image duke = new Image(moominUrl, 160, 60, false, true);

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the required objects.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Gets greeting message
     *
     * @return Greeting message
     */
    public String getGreetingMessage() {
        return ui.getGreetingMessage();
    }

    /**
     * Gets response for the inputs entered by the user
     *
     * @return Response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.getResponse(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
