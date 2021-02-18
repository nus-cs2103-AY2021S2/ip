package duke;

import duke.system.*;
import duke.system.view.Gui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Duke{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;
    private Parser in;

    /**
     * initiate UI and try to load in the data if exist, else initiate a new list and show error
     * @param filePath the path where the stored txt is saved
     */
    public Duke(){
        in = new Parser();
        tasks = new TaskList();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input, TaskList tasks) {
        in = new Parser(input);
        return in.print(tasks);
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
