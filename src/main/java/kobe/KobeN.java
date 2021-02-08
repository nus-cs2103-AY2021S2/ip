package kobe;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

public class KobeN extends Application {
    private Storage storage;
    private TaskList tasks; //ArrayList<Tasks> tasks
    private Ui ui;
    private static final String HOME = System.getProperty("user.home");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public KobeN() {}

    /**
     * Initialises Kobe.
     */
    public KobeN(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks, ui);
    }

    /**
     * Main method. Allows Kobe to run.
     */
    public static void main(String[] args) {
        Path path = Paths.get(HOME + "/ip/src/main/data/kobe.txt");
        String pathName = HOME + "/ip/src/main/data/kobe.txt";
        new KobeN(pathName).run();
    }

    /**
     * Runs Kobe, ready to accept commands typed into the command line.
     */
    public void run() {
        //Scanner things
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                //Read the whole line, dissect each command word, including the condition after "/"
                String command = sc.nextLine();

                //---Parser---
                Parser.readInput(command, tasks, storage, ui);
            }
        } catch (CustomExceptions.IncompleteDecriptionException e) {
            System.out.println(e);
        } catch (CustomExceptions.IncorrectDecriptionException e) {
            System.out.println(e);
        }

        sc.close();

    }

    /**
     * Building JavaFX GUi
     *
     * @param stage
     */
    @Override
    public void start (Stage stage) {
        //Step 1. Set up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Format the windown to an expected look
        stage.setTitle("Kobe");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //not shown
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); //shown when needed

        scrollPane.setVvalue(1.0); //current vertical scroll position
        scrollPane.setFitToWidth(true); //fit to width

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        //Step 3: Add functionality to handle user input
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing input text
     * @return  a label with the specified user input text with word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true); //text wrapping (auto line creation)

        return textToAdd;
    }

}