//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class Duke {

    private boolean isActive;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.isActive = true;
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new Ui();
        this.parser = new Parser(taskList, ui);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }
}
