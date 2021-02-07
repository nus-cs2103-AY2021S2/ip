package com.lirc572.ip;

import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The entry point of the program.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private String userImageSrc;
    private String dukeImageSrc;

    /**
     * The task list.
     */
    private final TaskList tasks;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        this.tasks = new TaskList();
        Storage.readFromFile(this.tasks);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Step 1. Setting up required components

        this.userImageSrc = "/images/dialogPic/User/majo_saya2.jpg";
        this.dukeImageSrc = "/images/dialogPic/Elaina/majo_elaina1.jpg";

        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(
                this.scrollPane,
                this.userInput,
                sendButton
        );

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.

        //Step 2. Formatting the window to look as expected

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385.0, 535.0);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogContainer.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        this.dialogContainer.setSpacing(10.0);

        this.userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(this.userInput, 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        //Step 3. Add functionality to handle user input.

        sendButton.setOnMouseClicked((event) -> {
            this.sendCommand();
        });

        this.userInput.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.sendCommand();
            }
        });

        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 4. Print welcome message.
        for (String line : this.getWelcomeText(false).split("\n")) {
            this.dialogContainer.getChildren().add(
                    DialogBox.getDukeDialogBox(line, this.dukeImageSrc)
            );
        }
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void sendCommand() {
        String userText = userInput.getText();
        if (userText.equals("")) {
            return;
        }
        String dukeText = this.getResponse(userInput.getText());
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(userText, this.userImageSrc),
                DialogBox.getDukeDialogBox(dukeText, this.dukeImageSrc)
        );
        this.userInput.clear();
        if (dukeText.equals("Bye. Hope to see you again soon!\n")) {
            Platform.exit();
            System.exit(0);
        }
    }

    private String getResponse(String input) {
        String response = null;
        try {
            response = Parser.processCommand(input, this.tasks);
        } catch (Exception e) {
            String errorText = "";
            errorText += Ui.printError(e);
            response = errorText;
        }
        return response;
    }

    private String getWelcomeText(boolean withLogo) {
        String welcomeText = "";
        if (withLogo) {
            welcomeText += Ui.printLogo();
            welcomeText += Ui.printEmptyLine();
        }
        welcomeText += Ui.printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        welcomeText += Ui.printLine("Sou, watashi desu!");
        return welcomeText;
    }

    /**
     * Starts the REPL.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print(
                Ui.printHorizontalLine()
                        + Ui.printEmptyLine()
                        + this.getWelcomeText(true)
                        + Ui.printHorizontalLine()
                        + Ui.printEmptyLine()
        );
        for (; ; ) {
            try {
                String response = Parser.processCommand(sc.nextLine(), this.tasks);
                if (response.equals("")) {
                    break;
                }
                System.out.print(response);
            } catch (Exception e) {
                String errorText = "";
                errorText += Ui.printError(e);
                errorText += Ui.printEmptyLine();
                System.out.print(errorText);
            }
        }
        sc.close();
    }

    /**
     * Constructs a Duke object and run it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Start in CLI mode
        // new Duke().run();

        // Start in GUI mode
        launch();
    }
}
