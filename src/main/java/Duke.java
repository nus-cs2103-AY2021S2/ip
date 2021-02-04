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
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        stage.setScene(scene);
        stage.show();


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





