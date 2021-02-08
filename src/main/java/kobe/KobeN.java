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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private Image kobe = new Image(this.getClass().getResourceAsStream("/images/Loppie says hi.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Shibaban action.png"));

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
     * Building JavaFX GUI
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
            handleUserInput();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
    }


    /**
     * Creates 2 dialog boxes, one echoing user input, the other containing Kobe's reply.
     * Both boxes are appended to the dialog container.
     * User input cleared afterwards.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label kobeText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(kobeText, new ImageView(kobe))
        );

        userInput.clear();
    }

    /**
     * The user input is a command that is processed, and the corresponding line that
     * Kobe is suppose to respond with is obtained in this method.
     *
     * @param input
     * @return
     */
    private String getResponse(String input) {
        try {
            Parser.readInput(input, tasks, storage, ui);
        } catch (CustomExceptions.IncompleteDecriptionException e) {
//            System.out.println(e);
            e.printStackTrace(); //Unwraps cause within InvocationTargetException
            //Since the reflection layer will wrap any exception in an InvocationTargetException
        } catch (CustomExceptions.IncorrectDecriptionException e) {
//            System.out.println(e);
            e.printStackTrace();
        } finally {
            return "Kobe heard: " + input;
        }
    }
}