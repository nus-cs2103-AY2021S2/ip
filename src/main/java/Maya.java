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
import javafx.scene.image.ImageView;
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

    private String filePath = "data/task.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    @Override
    public void start(Stage stage) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = storage.load();
        } catch(UnknownCommandException | NoSuchElementException
                | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println(e.getMessage());
        }

        //The container for the content of the chat to scroll.
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getMayaDialog(dukeText, new ImageView(maya))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            return Parser.parse(input, ui, taskList, storage);
        } catch (UnknownCommandException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the Maya object to accept user commands.
     * Processes user commands by utilising Parser object
     * to make sense of the user command.
     *
     * @param filePath a String of the path
     *                 where the TaskList is stored.
     */
    public void run(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = storage.load();

            ui.showWelcome();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String command = sc.next();
                ui.showLine();

                System.out.println(Parser.parse(command, ui, taskList, storage));

                // To exit the program with the command "bye"
                if (command.equals("bye")) {
                    break;
                }
            }
            sc.close();
        } catch(UnknownCommandException | NoSuchElementException
                | ArrayIndexOutOfBoundsException | IOException e){
            System.out.println(e.getMessage());
        } finally{
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Maya().run("data/task.txt");
    }
}
