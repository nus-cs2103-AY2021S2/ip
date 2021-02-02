package duke;

// JavaFX Imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


    }

    /**
     * Prints the greeting for the chatbot.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        dialogContainer.getChildren().add(getDialogLabel("Hello from\n" + logo));
    }

    /**
     * This method initiates the chatbot of the Duke program. It call upon various
     * classes responsible for different functionalities of Duke.
     */
    @Override
    public void start(Stage stage) {
        Parser p = new Parser();

        Storage st = new Storage("tasklist");
        TaskList tl = st.loadTaskList();

        // Initialization
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

        // Formatting
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

        greeting();

        // Functionality
        sendButton.setOnMouseClicked((event) -> {
            String input = userInput.getText();

            dialogContainer.getChildren().add(getDialogLabel(">> " + input));
            userInput.clear();

            // Call Parser
            String out = p.process(input, tl);
            dialogContainer.getChildren().add(getDialogLabel(out));

            if (p.checkEnd()) {

                st.saveTaskList(tl);

                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 3 ")));
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 2 ")));
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 1 ")));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        dialogContainer.getChildren().add(getDialogLabel(e.toString()));
                    }

                    System.exit(0);
                }).start();

            }
        });

        userInput.setOnAction((event) -> {
            String input = userInput.getText();

            dialogContainer.getChildren().add(getDialogLabel(">> " + input));
            userInput.clear();

            // Call Parser
            String out = p.process(input, tl);
            dialogContainer.getChildren().add(getDialogLabel(out));

            if (p.checkEnd()) {

                st.saveTaskList(tl);

                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 3 ")));
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 2 ")));
                        Thread.sleep(1000);
                        Platform.runLater(() -> dialogContainer.getChildren().add(getDialogLabel("Application exiting in 1 ")));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        dialogContainer.getChildren().add(getDialogLabel(e.toString()));
                    }

                    System.exit(0);
                }).start();

            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));



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
}
