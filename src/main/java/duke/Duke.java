package duke;

import java.util.List;

import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

/**
 * Duke class that simulates the running of the Duke Program
 */
public class Duke extends Application {

    /** Storage instance that is used by Duke during run for loading and writing of file*/
    private Storage storage;

    /** TaskList instance used by Duke during run that manages the tasks */
    private TaskList tasks;

    /** Ui instance used by Duke during run to interact with User */
    private Ui ui;

    private String filePath = "./data/tasks.txt";
    
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/cat3.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/cat2.jpg"));

    /**
     * Constructor for the Duke class
     */
    public Duke() {

        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());

        } catch (DukeException e) {

            ui.showLoadingError();
            tasks = new TaskList();

        }
    }

    /**
     * Runs the Duke Program. The Duke Program will watch for user input and react accordingly to the
     * user input in this method
     */
    public void run() {
        ui.printDivider();
        ui.printWelcome();
        ui.printDivider();

        try {
            boolean carryOn = true;

            while (carryOn) {

                String action = ui.read();

                Parser parser = new Parser(action);
                parser.check();

                String[] parsedAction = parser.getParsedAction();

                switch (parsedAction[0]) {
                case "todo":
                    ui.printDivider();
                    ui.addPrint();
                    ToDoTask todo = tasks.handleToDoTask(action);
                    ui.printTask(todo);
                    ui.countTasks(tasks);
                    ui.printDivider();

                    break;

                case "deadline":
                    ui.printDivider();

                    ui.addPrint();

                    DeadlineTask deadlineTask = tasks.handleDeadlineTask(action);

                    ui.printTask(deadlineTask);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "event":
                    ui.printDivider();

                    ui.addPrint();

                    EventTask eventTask = tasks.handleEventTask(action);

                    ui.printTask(eventTask);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "list":
                    ui.printDivider();

                    ui.printStored(tasks);

                    ui.printDivider();

                    break;

                case "done":
                    int number = Integer.valueOf(parsedAction[1]);
                    ui.printDivider();

                    ui.printMarked();

                    Task completed = tasks.handleDone(number);

                    ui.printTask(completed);

                    ui.printDivider();
                    break;

                case "check":
                    ui.printDivider();

                    String result = tasks.findOnDateTasks((parsedAction[1]));

                    ui.print(result);

                    ui.printDivider();

                    break;

                case "bye":
                    carryOn = false;

                    break;

                case "delete":
                    int index = Integer.valueOf(parsedAction[1]);

                    ui.printDivider();

                    ui.printRemoved();

                    Task task = tasks.handleDelete(index);

                    ui.printTask(task);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "find":
                    String keyword = parsedAction[1];

                    ui.printDivider();
                    ui.printMatching();

                    List<Task> matches = tasks.getMatch(keyword);

                    ui.printList(matches);
                    ui.printDivider();

                    break;

                default:
                    throw new UnknownInputException();
                }
            }

            ui.printDivider();

            ui.printBye();

            ui.printDivider();

            storage.write(tasks);

        } catch (DukeException e) {
            ui.printDivider();

            ui.print(e.getMessage());

            ui.printDivider();
        }
        
    }

    /**
     * main driver method to run the Duke program
     * @param args variable arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
    
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
    
        //Part 3. Add functionality to handle user input.
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
        // You will need to import `javafx.scene.control.Label`.
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
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
    
}
