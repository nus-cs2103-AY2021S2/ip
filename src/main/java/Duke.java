import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
Need to address:
- make a package
- add more j-unit tests
 */

/**
 * Main class which brings all the other classes together.
 */
public class Duke extends Application {
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user2.jpg"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/luna.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    @Override
    public void start(Stage stage) {

        TaskList.checkFileFolderSpecifications();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        Label greeting = new Label(Ui.greet());
        greeting.setFont(new Font("Didact Gothic", 12));

        Circle c = new Circle(200, 200, 40);
        c.setFill(new ImagePattern(duke));

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, c));
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(600.0, 630.0);

        scrollPane.setPrefSize(600, 610);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        userInput.setPrefWidth(535.0);
        sendButton.setPrefWidth(60.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        String userInp = userInput.getText();
        String userInpResponse = getResponse(userInp);

        Label userText = new Label("\n" + userInp);
        userText.setFont(new Font("Courier new", 18));

        Label dukeText = new Label("\n" + userInpResponse + "\n");
        dukeText.setFont(new Font("Didact Gothic", 11));

        if (userInpResponse.equals(Ui.invalidKeywordExceptionMessage())) {
            dukeText.setStyle("-fx-background-color:rgba(255, 255, 0, 0.7)");
        }

        if (userInpResponse.contains(Ui.invalidTaskFormatBasicExceptionMessage())) {
            dukeText.setStyle("-fx-background-color:rgba(255, 105, 180, 0.7)");
        }

        Circle c = new Circle(200, 200, 40);
        c.setFill(new ImagePattern(duke));

        Circle c2 = new Circle(200, 200, 40);
        c2.setFill(new ImagePattern(user));

        Rectangle r = new Rectangle(200, 200, 80, 80);
        r.setArcWidth(30.0);
        r.setArcHeight(20.0);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, c2),
                DialogBox.getDukeDialog(dukeText, c)
        );

        if (userInput.getText().equals("bye")) {
            Platform.exit();
            System.exit(0);
        }

        userInput.clear();
    }

    private String getResponse(String input) {
        return Parser.parse(input);
    }
}
