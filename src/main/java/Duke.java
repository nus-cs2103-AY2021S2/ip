import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class for duke
 */
public class Duke  {

    private Storage storage = new Storage();
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser = new Parser();
    private Image user = new Image(this.getClass().getResourceAsStream("/images/img.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/img_1.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        ui =  new Ui();
        assert ui != null : "error initializing ui";
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "error initializing tasks";
        }
        catch(IOException | EmptyArgumentException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     *
     * @throws EmptyArgumentException empty argument exception
     * @throws BadDateException bad date exception
     * @throws InvalidCommandException invalid command exception
     * @throws IOException ioexception
     */

    public String getResponse(String line) {
        Command command = parser.parseCommand(line);
        if(command.isExit()) return "Bye bye";
        return tasks.run(command,storage);
    }
    public String welcome() {
        ui.sendWelcomeMessage();
        return "Hi Im Duke, how may I help you?";
    }
}