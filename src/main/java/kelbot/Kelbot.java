package kelbot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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


public class Kelbot extends Application {
    private Path path = Paths.get(".", "data", "Kelbot.txt");
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(getClass().getResourceAsStream("/images/User.jpg"));
    private Image kelbot = new Image(getClass().getResourceAsStream("/images/Kelbot.jpg"));
    /**
     * Initializes Kelbot
     */
    public Kelbot() {
        ui = new UI();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (KelbotException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
        stage.setTitle("Kelbot");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
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
        String userText = userInput.getText();
        String kelbotText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getKelbotDialog(kelbotText, kelbot)
        );
        userInput.clear();
    }
    /**
     * Get response from Kelbot
     * @param input The input from user
     */
    public String getResponse(String input) {
        return "Kelbot has received: " + input;
    }
    /**
     * The main programme that runs after the Kelbot initialization.
     */
    public void run() {
        while (true) {
            try {
                Parser parser = ui.takeInput();
                Command command = parser.getCommand();
                int taskNumber = parser.getTaskNumber();
                String keyword = parser.getKeyword();
                String taskName = parser.getTaskName();
                LocalDate date = parser.getDate();
                if (command == Command.BYE) {
                    ui.sayGoodbye();
                    break;
                } else if (command == Command.LIST) {
                    ui.printList(taskList);
                } else if (command == Command.DONE || command == Command.DELETE) {
                    try {
                        if (taskNumber == 0) {
                            throw new KelbotException("Which task are you referring to?");
                        } else if (command == Command.DONE) {
                            Task task = taskList.complete(taskNumber);
                            ui.printDone(task);
                        } else {
                            try {
                                Task task = taskList.delete(taskNumber);
                                ui.printDelete(task);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("The list is empty");
                            }
                        }
                    } catch (KelbotException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command == Command.FIND) {
                    try {
                        if (keyword.equals("")) {
                            throw new KelbotException("Keyword cannot be empty!");
                        } else {
                            TaskList taskListToPrint = new TaskList(taskList.search(keyword));
                            ui.printRelevantTasks(taskListToPrint);
                        }
                    } catch (KelbotException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        if (taskName == "") {
                            throw new KelbotException("Task name cannot be empty!");
                        } else if (command == Command.TODO) {
                            TodoTask newTodoTask = new TodoTask(taskName);
                            taskList.add(newTodoTask);
                            ui.printAdd(newTodoTask, taskList.getSize());
                        } else if (date == null) {
                            throw new KelbotException("Date cannot be empty!");
                        } else if (command == Command.DEADLINE) {
                            DeadlineTask newDeadlineTask = new DeadlineTask(taskName, date);
                            taskList.add(newDeadlineTask);
                            ui.printAdd(newDeadlineTask, taskList.getSize());
                        } else {
                            EventTask newEventTask = new EventTask(taskName, date);
                            taskList.add(newEventTask);
                            ui.printAdd(newEventTask, taskList.getSize());
                        }
                    } catch (KelbotException e) {
                        System.out.println(e.getMessage());
                    }
                }
                storage.save(taskList.getTaskList());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date");
            }
        }
    }
    /**
     * Gets Task List
     *
     * @return Task List
     */
    public TaskList getTaskList() {
        return taskList;
    }
    /**
     * Main programme
     */
    public static void main(String[] args) throws KelbotException {
        new Kelbot().run();
    }
}
