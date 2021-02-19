package vergil.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import vergil.components.Ui;
import vergil.components.Storage;
import vergil.components.Parser;
import vergil.components.TaskList;
import vergil.components.DialogBox;

import vergil.types.exceptions.VergilException;

/**
 * Represents the Vergil chatbot application.
 */
public class Vergil extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("resources/user.png"));
    private Image vergil = new Image(this.getClass().getResourceAsStream("resources/vergil.png"));

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a Vergil object with a specified save file path.
     */
    public Vergil() {
        ui = new Ui();
        storage = new Storage("data/vergil_list.sav");
        parser = new Parser();

        try {
            taskList = new TaskList(storage.load());
        } catch (VergilException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Initializes the GUI window and displays it.
     *
     * @param stage (fill using overridden def.)
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        Label vergilWelcomeText = new Label(ui.getWelcomeMessage());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Vergil");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren()
                .add(getVergilDialog(vergilWelcomeText, new ImageView(vergil)));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a new dialog box representing the user's input.
     *
     * @param   l   the label containing the user's input.
     * @param   iv  the ImageView object containing the user's image.
     * @return      a dialog box containing the user's input and image.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates a new dialog box representing Vergil's response.
     *
     * @param   l   the label containing Vergil's response.
     * @param   iv  the ImageView object containing Vergil's image.
     * @return      a dialog box containing Vergil's response and image.
     */
    public static DialogBox getVergilDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    /**
     * Retrieves Vergil's response to the the execution of a given command.
     *
     * @param   command the user's command to be executed.
     * @return          Vergil's response after the command has been executed.
     */
    public String getResponse(String command) {
        try {
            return parser.parse(command).execute(ui, taskList, storage);
        } catch (VergilException e) {
            return "Sorry! " + e.getMessage();
        }
    }

    /**
     * Handles the user's input by displaying it, executing it,
     * displaying Vergil's response, and clearing the input's containing text box.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label vergilText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                getUserDialog(userText, new ImageView(user)),
                getVergilDialog(vergilText, new ImageView(vergil))
        );

        userInput.clear();

        if (vergilText.getText().equals(ui.getFarewellMessage())) {
            System.exit(0);
        }
    }

    /**
     * Main method to run Vergil.
     *
     * @param   args    arguments given to Vergil (not used).
     */
    public static void main(String[] args) {
        // Do nothing.
    }
}
