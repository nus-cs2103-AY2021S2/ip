package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Manages the exchange of user input and program output.
 */
public class Ui extends Application {
    public static final String LOGO = "D U K E";
    protected Scanner scanner;
    protected TaskList tasks;
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initialises the input scanner and task list.
     */
    public void initialise() {
        scanner = new Scanner(System.in);

        // Initialise task list
        tasks = new TaskList();
        try {
            tasks.load();
        } catch (IOException ex) {
            System.out.println("  Unable to load tasks.");
        }
    }

    /**
     * Creates the GUI for user to communicate with Duke.
     *
     * @param stage The stage for the GUI.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initialise();

        // Create nodes for GUI
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Arrange nodes for GUI
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        // Set anchor points for GUI
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Auto scroll to bottom of screen
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Display startup message from Duke
        String startMessage = "  Hello from\n    " + LOGO + "\n"
                + "  Please input a command.";
        displayResponse(startMessage);

        // Provide user with interactive actions
        sendButton.setOnMouseClicked((event) -> processUserInput());
        userInput.setOnAction((event) -> processUserInput());
    }

    /**
     * Processes user input and displays response by Duke.
     */
    private void processUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(getDialogBox("\n" + input));
        userInput.clear();

        String output = "";
        try {
            output = Parser.process(input, this.tasks);
        } catch (DukeException ex) {
            displayResponse(ex.toString());
        }
        if (output == null) {
            displayResponse("  Can you read this before the UI closes?");
            stage.close();
        } else if (!output.equals("")) {
            displayResponse(output);
        }
    }

    /**
     * Creates a box with the specified text.
     *
     * @param text String containing text to add.
     * @return A box with the specified text that has word wrap enabled.
     */
    private HBox getDialogBox(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return new HBox(textToAdd);
    }

    /**
     * Displays the response from Duke in a text box with black border.
     *
     * @param response String to display.
     */
    private void displayResponse(String response) {
        HBox dialogBox = getDialogBox("\n" + response);
        dialogBox.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK"),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        dialogContainer.getChildren().add(dialogBox);
    }
}
