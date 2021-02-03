package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

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

/**
 * Duke is a program for task tracking.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    public Duke() {

    }
    /**
     * Constructor for Duke.
     *
     * @param filePath a path representing the location of duke save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Runner for duke.
     * Read, process input and will output accordingly.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String request = "";

        while (!request.equals("bye")) {
            Parser parser = new Parser(sc.nextLine());
            request = parser.getRequest();
            String args = parser.getArgs();

            if (request.equals("bye")) {
                ui.showBye();
                break;
            } else if (request.equals("list")) {
                ui.printList(tasks);
            } else if (request.equals("done")) {
                try {
                    int taskNo = Integer.parseInt(args);
                    ui.printMarked(tasks.markDone(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("delete")) {
                try {
                    int taskNo = Integer.parseInt(args);
                    ui.printRemoved(tasks, tasks.removeTask(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("todo")) {
                try {
                    Task task = Task.createTask(args, request, "", "");
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("deadline")) {
                try {
                    String[] deadStr = parser.getFormattedCommand();
                    Task task = Task.createTask(deadStr[0], request, deadStr[1], deadStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("event")) {
                try {
                    String[] eventStr = parser.getFormattedCommand();
                    Task task = Task.createTask(eventStr[0], request, eventStr[1], eventStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("find")) {
                ui.printFound(tasks.findTask(args));
            } else {
                try {
                    throwDontKnowException();
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Throw DukeException with fixed message.
     * This method will always throw a DukeException with a fixed message. If
     * user input is invalid, this method can be used to throw an exception.
     *
     * @throws DukeException
     */
    public static void throwDontKnowException() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        stage.setScene(scene);
        stage.show();
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
