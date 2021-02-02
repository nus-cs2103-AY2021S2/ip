import java.io.IOException;

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
import percy.command.Command;
import percy.command.Parser;
import percy.exception.PercyException;
import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.ConfirmBox;
import percy.ui.DialogBox;
import percy.ui.Ui;

public class Gui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Stage window;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image percyImage = new Image(this.getClass().getResourceAsStream("/images/percy.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    @Override
    public void start(Stage stage) throws IOException, PercyException {
        this.window = stage;

        Percy percy = new Percy("data/percy.txt");
        storage = percy.getStorage();
        tasks = percy.getTasks();
        ui = percy.getUi();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        window.setTitle("percy");
        window.setResizable(false);
        window.setMinHeight(600.0);
        window.setMinWidth(400.0);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400.0, 570.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(330.0);

        sendButton.setPrefWidth(65.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
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

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (PercyException e) {
                ui.makeMsg(e.getMessage());
            } catch (IOException io) {
                ui.makeMsg(io.getMessage());
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (PercyException e) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getPercyDialog(new Label(ui.makeMsg(e.getMessage())), new ImageView(percyImage)));
            } catch (IOException io) {
                ui.makeMsg(io.getMessage());
            }
        });


        dialogContainer.getChildren().addAll(
                DialogBox.getPercyDialog(new Label(ui.makeStartUpMsg()), new ImageView(percyImage)));
        scene = new Scene(mainLayout);

        window.setScene(scene);
        window.show();
    }

    /**
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
     * Creates two dialog boxes, one echoing user input and the other containing percy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws PercyException, IOException {
        Label userText = new Label(userInput.getText());
        Label percyText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getPercyDialog(percyText, new ImageView(percyImage)));
        userInput.clear();
    }

    private String getResponse(String input) throws PercyException, IOException {
        String command = Ui.readCommand();
        Parser parser = new Parser(command);
        Command cmd = parser.getCommand();
        String response = cmd.execute(tasks, storage);
        System.out.println(response);
        if (cmd.isExit()) {
            closeProgram();
        }
        return response;
    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("percy", "Are you sure you want to exit?");
        if (answer) {
            window.close();
        }
    }
}
