package duke;

import java.util.Scanner;

import duke.system.DialogBox;
import duke.system.Parser;
import duke.system.Storage;
import duke.system.Ui;
import duke.system.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/memeeman.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/memeeman.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser in;

    /**
     * initiate UI and try to load in the data if exist, else initiate a new list and show error
     * @param filePath the path where the stored txt is saved
     */
    public Duke(){
        ui = new Ui();
        in = new Parser();
        tasks = new TaskList();
    }

    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input, TaskList tasks) {
        in = new Parser(input);
        return in.print(tasks);
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Overrides the Application#Start method from javafx and setup the scene
     * @param stage
     */
    @Override
    public void start(Stage stage) {
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * The method to be called in <code>void main()</code> that
     * keep taking in commands until "bye" is entered
     */
    public void run() {
        Parser in = new Parser();
        Scanner sc= new Scanner(System.in);
        ui.showLine();
        ui.showText("Hello! I'm Duke \nWhat can I do for you? \n");
        ui.showLine();
        while(!in.getCommand().equals("bye")){
            in = new Parser(sc.nextLine());
            ui.showText(in.print(tasks));
            try {
                storage.writeData(tasks.toString());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
