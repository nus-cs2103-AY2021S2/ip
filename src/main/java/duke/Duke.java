package duke;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;


/**
 * Duke is a chatbot that helps the user to keep track of tasks that needs to be done.
 */

public class Duke {

    /**
     * main method run
     *
     * @param args
     * @throws DukeException
     * @throws Exception
     */
    public static void main(String[] args) throws DukeException, Exception {
        Ui.greet();
        Parser.read();
    }
}
