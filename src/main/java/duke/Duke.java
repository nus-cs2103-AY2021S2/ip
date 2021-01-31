package duke;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;

/**
 * Driver program for Duke.
 */
public class Duke {

    private static final Path storagePath = Paths.get(".", "data", "duke.txt");
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage(Duke.storagePath);
        this.taskList = new TaskList(this.storage.read());
        if (taskList.size() == 0) {
            this.taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String response = Parser.parse(input, this.taskList, this.storage);
            if (response.contains("Bye")) {
                Platform.exit();
            }
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

}
