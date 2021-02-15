import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Driver class that takes in input and performs certain functions based on input.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Start method part of Application interface.
     * @param stage Stage parameter which forms the base object to build the GUI
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        //Step 1. Formatting the window to look as expected.
        Ui ui = new Ui();
        Storage storage = new Storage();

        // Creating directory if it does not exist
        storage.createDirectory();

        // Loading file from directory if it exists
        List<Task> tempList = storage.loadFile();
        TaskList taskList = new TaskList(tempList);

        //The container for the content of the chat to scroll.
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Label opener = new Label(ui.printStart());
        dialogContainer.getChildren().add(opener);


        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput(taskList.taskList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput(taskList.taskList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(List<Task> taskList) throws IOException {
        Label dukeText = new Label(getOutput(userInput.getText(), taskList));
        dialogContainer.getChildren().add(dukeText);
        userInput.clear();
    }


    /**
     * Helper method that processes the user input and returns corresponding for the
     * user. GUI version.
     * @param command User input, e.g. list, deadline...
     * @param taskListStored Retrieved task list from local disk
     * @return String to be displayed to user in dialogue box
     * @throws IOException
     */
    private String getOutput(String command, List<Task> taskListStored) throws IOException {
        Parser parser = new Parser();
        Ui getInput = new Ui();
        TaskList taskList = new TaskList(taskListStored);
        Storage storage = new Storage();

        String output = "";

        if (command.equals("Hello")) {
            output = getInput.printStart();
        } else if (command.startsWith("list")) {
            output = taskList.enumerateTasks();
        } else if (command.startsWith("done")) {
            String[] taskToDelete = command.split("\\s+");
            output = taskList.markAsDone(Integer.parseInt(taskToDelete[1]));
        } else if (command.startsWith("todo")) {
            try {
                Todo currentTask = new Todo(command.substring(5));
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidTodo();
            }
        } else if (command.startsWith("event")) {
            try {
                String[] splitString = command.split("/at");
                String eventDesc = splitString[0];
                String eventDate = splitString[1];
                Event currentTask = new Event(eventDesc.substring(6), eventDate);
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidEvent();
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] splitString = command.split("/by");
                String eventDesc = splitString[0];
                String eventDate = splitString[1];
                Deadline currentTask = new Deadline(eventDesc.substring(9), eventDate);
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidDeadline();
            }
        } else if (command.startsWith("delete")) {
            String[] splitString = command.split("\\s+");
            output = taskList.removeTask(Integer.parseInt(splitString[1]));
        } else if (command.startsWith("find")) {
            String keyword = command.split("\\s+")[1];
            output = taskList.retrieveByKeyword(keyword);
        } else if (command.startsWith("bye")) {
            output = getInput.outBye();
        } else {
            // Command is not recognized
            output = getInput.outNotRecognized();
        }
        // Saving updated tasks to local disk
        storage.saveFile(taskList.logAllTasks());

        return output;
    }


    /**
     * Main class to take in user input. CLI version.
     * @param params Standard user input params
     */
    public static void main(String[] params) throws IOException {
        Scanner sc = new Scanner(System.in);

        Ui getInput = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();

        // Starting string to prime the program
        String command = Ui.primer;

        // Creating directory if it does not exist
        storage.createDirectory();

        // Loading file from directory if it exists
        List<Task> tempList = storage.loadFile();
        TaskList taskList = new TaskList(tempList);

        while (parser.stillHaveCommands(command)) {
            if (command.equals("Hello")) {
                getInput.printStarter();
            } else if (command.startsWith("list")) {
                taskList.enumerateTasks();
            } else if (command.startsWith("done")) {
                String[] taskToDelete = command.split("\\s+");
                taskList.markAsDone(Integer.parseInt(taskToDelete[1]));
            } else if (command.startsWith("todo")) {
                try {
                    Todo currentTask = new Todo(command.substring(5));
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (StringIndexOutOfBoundsException indexError) {
                    getInput.invalidTodo();
                }
            } else if (command.startsWith("event")) {
                try {
                    String[] splitString = command.split("/at");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Event currentTask = new Event(eventDesc.substring(6), eventDate);
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    getInput.invalidEvent();
                }
            } else if (command.startsWith("deadline")) {
                try {
                    String[] splitString = command.split("/by");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Deadline currentTask = new Deadline(eventDesc.substring(9), eventDate);
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    getInput.invalidDeadline();
                }
            } else if (command.startsWith("delete")) {
                String[] splitString = command.split("\\s+");
                taskList.removeTask(Integer.parseInt(splitString[1]));
            } else if (command.startsWith("find")) {
                String keyword = command.split("\\s+")[1];
                taskList.retrieveByKeyword(keyword);
            } else {
                // Command is not recognized
                getInput.commandNotRecognized();
            }
            // Getting the next command from user
            command = sc.nextLine();
        }

        storage.saveFile(taskList.logAllTasks());

        getInput.printBye();

    }


}
