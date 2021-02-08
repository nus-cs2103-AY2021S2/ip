import duke.commands.DukeCommand;
import duke.commands.factory.DukeCommandFactory;
import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionFileNotAccessible;
import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.TaskList;

// Courtesy of https://se-education.org/guides/tutorials/javaFxPart2.html
import duke.ui.Ui;
import duke.ui.UiGui;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke extends Application {

    private FileLoader loader;
    private TaskList tasks;
    private Ui ui;
    private boolean isLocalTaskList; // in event file cannot be written to

    // JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final Font FONT = new Font("Consolas", 10);

    public Duke() {
    }

    /**
     * Initializes the main program given directory used to save user tasks.
     *
     * Attempts to load the task list from the file, displaying the status
     * of the task list depending on load success.
     */
    private void initDuke() {
        String filePath = "./dukeData/tasks.txt";
        ui.showWelcomeScreen();
        isLocalTaskList = false;
        try {
            loader = new FileLoader(filePath);
            tasks = loader.read();
            loader.throwIfNotWritable(); // can read but cannot write
            ui.showLoadingSuccess(tasks.size());

        } catch (DukeExceptionFileNotWritable e) {
            isLocalTaskList = true;
            ui.showFileWriteError(tasks.size());

        } catch (DukeExceptionFileNotAccessible e) {
            isLocalTaskList = true;
            tasks = new TaskList();
            ui.showFileLoadingError();

        } catch (DukeExceptionIllegalArgument e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        ui = new UiGui(dialogContainer);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(600);

        mainLayout.setPrefSize(600, 600);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525);

        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        initDuke();

    }

    private void handleUserInput() {
        try {
            String input = userInput.getText();
            ui.println(input);
            DukeCommand cmd = DukeCommandFactory.getDukeCommand(input);
            cmd.execute(tasks, ui, loader);
            if (cmd.isExit()) {
                Platform.exit();
            }
        } catch (DukeExceptionFileNotWritable e) {
            if (!isLocalTaskList) {
                ui.showError(e);
            }
        } catch (DukeException e) {
            ui.showError(e);
        }
        userInput.clear();
    }
}