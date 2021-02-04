import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.lang.reflect.Method;



/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *
 * @version 21 Jan 2021.
 * @author Zhang Peng.
 */
public class Duke extends Application  {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(new FileInputStream("2.png"));
    private Image duke = new Image(new FileInputStream("3.png"));

    public Duke() throws FileNotFoundException {
    }


    // need to change this start class
    @Override
    public void start(Stage stage) {

            //Step 1. Setting up required components
            //The container for the content of the chat to scroll.
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

            //Step 2. Formatting the window to look as expected
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
            AnchorPane.setTopAnchor(scrollPane, 1.0);
            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);
            AnchorPane.setLeftAnchor(userInput, 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);


            //Part 3. Add functionality to handle user input.
            sendButton.setOnMouseClicked((event) -> {
                handleUserInput();
            });

            userInput.setOnAction((event) -> {
                handleUserInput();
            });

            //Scroll down to the end every time dialogContainer's height changes.
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */




    // will need to change this box
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }




    /*
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> arrayList = new ArrayList<>();

        String path = "duke.txt";
        File dukeFile = new File("duke.txt"); // doest matter if the path exit
        boolean result;

        try {
            result = dukeFile.createNewFile();
            if (result) {
               System.out.println(".txt file created!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //read from the file content into the arrayList at the start.
        Scanner s = new Scanner(dukeFile);
        new Storage().loadingFile(arrayList, s);

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        new Ui().interactWithUser(arrayList, path);
    }
    */


}





