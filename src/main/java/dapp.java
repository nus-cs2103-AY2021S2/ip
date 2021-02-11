import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class dapp extends Application{

        private ScrollPane scrollPane;
        private VBox dialogContainer;
        private TextField userInput;
        private Button sendButton;
        private Scene scene;
        private Image user;
        private Image dook;


        public dapp(){
        }

        public static void main(String[] args) {
        }


        @Override
        public void start(Stage stage) {

            assert (new File("/images/DaUser.png")).exists(): "image file for user does not exist";
            assert (new File("/images/DaDuke.png")).exists(): "image file for dukebot does not exist";
            this.user =  new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
            this.dook = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


            //container
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);
            userInput = new TextField();
            sendButton = new Button("send");
            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
            scene = new Scene(mainLayout);
            stage.setScene(scene);
            stage.show();
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
            // You will need to import `javafx.scene.layout.Region` for this.
            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
            userInput.setPrefWidth(325.0);
            sendButton.setPrefWidth(55.0);
            AnchorPane.setTopAnchor(scrollPane, 1.0);
            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);
            AnchorPane.setLeftAnchor(userInput , 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

            //create new Duke object
            Duke duke = new Duke("src/main/java/data.txt");

            //step 3
            //do on click
            sendButton.setOnMouseClicked((event) -> {
                handleUserInput(duke);
            });

            userInput.setOnAction((event) -> {
                handleUserInput(duke);
            });


            dialogContainer.getChildren().addAll(
                    //DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(new Label(duke.lastOutListElement()), new ImageView(dook))
            );


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

        private void handleUserInput(Duke duke) {
            String userCommand = userInput.getText();
            Label userText = new Label(userCommand);

            String responseText = getResponse(userCommand, duke);
            Label dukeText = new Label(responseText);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dook))
            );
            if(userCommand.equals("bye")){
                Platform.exit();
            }
            userInput.clear();

        }
        
        private String getResponse(String userTest, Duke duke) {
            //return "Duke heard: " + input;
            duke.setCurrentCommand(userTest);
            return duke.returnOutput();
            //return duke.getCurrentCommand();


        }



}
