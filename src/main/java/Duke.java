import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

/** Describes the main class. */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    @Override
    public void start(Stage stage) {
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
        stage.setTitle("Sonia");
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
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
        String[] inputs = userInput.getText().split(" ");
        Command c = Parser.parse(inputs);
        String response = this.executeCommand(c);
        Label responseText = new Label(response);
        dialogContainer.getChildren().add(new DialogBox(responseText));
        userInput.clear();
    }

    /**
     *
     */
    public Duke() {
        this("./data.txt");
    }
    /**
     * Returns a Duke object that takes in a file path.
     *
     * @param path The path to the storage file
     */
    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui("Sonia");

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes the given command.
     *
     * @param c The command object
     */
    public String executeCommand(Command c) {
        try {
            if (c.type == CommandType.ADD_TODO) {
                if (c.args.size() != 1) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addTodo(c.args.get(0));
                return ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.ADD_DEADLINE) {
                if (c.args.size() != 2) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addDeadline(c.args.get(0), c.args.get(1));
                return ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.ADD_EVENT) {
                if (c.args.size() != 2) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addEvent(c.args.get(0), c.args.get(1));
                return ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.COMPLETE_TASK) {
                int id = Integer.parseInt(c.args.get(0));
                tasks.completeTask(id);
                return ui.echo(ui.COMPLETE_TASK);
            } else if (c.type == CommandType.DELETE_TASK) {
                int id = Integer.parseInt(c.args.get(0));
                tasks.deleteTask(id);
                return ui.echo(ui.DELETE_TASK);
            } else if (c.type == CommandType.FIND_TASKS) {
                return tasks.findTasks(c.args.get(0));
            } else if (c.type == CommandType.LIST_TASKS) {
                return tasks.showTasks();
            } else if (c.type == CommandType.TERMINATE) {
                this.terminate();
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeInvalidArgumentException e) {
            return ui.echo(ui.INVALID_ARGUMENT);
        } catch (DukeInvalidCommandException e) {
            return ui.echo(ui.INVALID_COMMAND);
        }
        return "";
    }

    /**
     * Immediately stops the program.
     */
    public void terminate() {
        storage.save(tasks);
        System.exit(0);
    }

    /**
     * The starting point of the class
     * @param args Default arguments supplied
     */
    public static void main(String[] args) {
        Duke sonia = new Duke("./data.txt");
        sonia.ui.greeting();
        System.out.print("You: ");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Command c = sonia.ui.prompt(sc);
            sonia.executeCommand(c);
            System.out.print("You: ");
        }

    }
}
