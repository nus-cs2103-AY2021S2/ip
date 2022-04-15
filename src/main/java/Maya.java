import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Maya extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image maya = new Image(this.getClass().getResourceAsStream("/images/maya.jpg"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/tucker.jpg"));

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private CommandHandler commandHandler;

    /**
     * Initiates an object of Maya with a Ui and Storage object.
     *
     * @param filePath a String that represents the path to store the file of Tasks.
     */
    public Maya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = storage.load();
        } catch (UnknownCommandException | NoSuchElementException
                | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println(e.getMessage());
        }

        commandHandler = new CommandHandler(ui, taskList, storage);
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Maya");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400, 545);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String mayaText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getMayaDialog(mayaText, maya)
        );

        userInput.clear();
    }

    /**
     * Gets the response to the user input.
     * @param input a String representing the user input
     * @return a String representing Maya's response to user input
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, commandHandler);
        } catch (MayaException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the Maya object to accept user commands.
     * Processes user commands by utilising Parser object
     * to make sense of the user command.
     */
    public void run() {
        try {
            taskList = storage.load();

            ui.showWelcome();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String command = sc.next();
                ui.showLine();

                System.out.println(Parser.parse(command, commandHandler));

                // To exit the program with the command "bye"
                if (command.equals("bye")) {
                    break;
                }
            }
            sc.close();
        } catch (MayaException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Maya("data/task.txt").run();
    }
}
