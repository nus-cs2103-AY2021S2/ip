import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
Need to address:
- make a package
- add more j-unit tests
 */

/**
 * Main class which brings all the other classes together.
 */
public class Duke {

    Duke() {
        TaskList.checkFileFolderSpecifications();
    }

    /**
     * Parses the user input and returns the appropriate response.
     * @param input the user input.
     * @return Luna's response.
     */
    public String getResponse(String input) {
        return Parser.parse(input);
    }
}
