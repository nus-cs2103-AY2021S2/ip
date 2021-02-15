package moomin;

import moomin.command.Command;
import moomin.exception.DukeException;
import moomin.parser.Parser;
import moomin.storage.Storage;
import moomin.task.TaskList;
import moomin.ui.Ui;
import javafx.scene.image.Image;

public class Moomin {

    private String littleMyUrl =
            "https://static.wikia.nocookie.net/moomin/images/0/05/My1.png/"
                    + "revision/latest/top-crop/width/300/height/300?cb=20190914020308";
    private String moominUrl =
            "https://i.pinimg.com/originals/19/3a/15/193a1552cc00589da96c9c8ce8cc4ba9.png";
    private Image user = new Image(littleMyUrl, 160, 60, false, true);
    private Image duke = new Image(moominUrl, 160, 60, false, true);

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Boolean isExited = false;

    /**
     * Sets up the required objects.
     */
    public Moomin() {
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
     * Gets greeting message.
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
        if (isExited) {
            return null;
        }
        try {
            Command command = Parser.parse(input);
            isExited = command.isExitCommand();
            return command.getResponse(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
