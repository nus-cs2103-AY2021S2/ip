package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

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



/**
 * The Duke program is a program that can help you
 * with the schedule management such as adding events, deadlines and todos
 * to it and it can also help you manage the tasks such as deleting tasks.
 *
 * Hope you like it!
 *
 * @author skinnychenpi
 * @since 2021-01-18
 */
public class Duke extends Application {
    private Scanner keyboard;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * A default constructor for Duke class
     */
    public Duke(){}



    @Override
    public void start(Stage stage) {
        TaskList taskList = new TaskList();


        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);

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


        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // This part is to read the txt file.
        keyboard = new Scanner(System.in);
        storage = new Storage("./data/duke.txt", "./data");
        try {
            taskList = storage.readTasks(taskList);
        } catch (Exception e) {
            String botMessage = "OOPS! There is something wrong: " + e.getMessage();
            Label dukeText = new Label(botMessage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
        }

        //Part 3. Add functionality to handle user input.
        TaskList finalTaskList = taskList;
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(finalTaskList);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(finalTaskList);
        });

        stage.show();


        // Save the taskList to the txt file. Due to the property of Lambda, the final task list can't be
        // extracted after execution, and hence the save part will only save the imported tasks. Need to fix.
        try {
            storage.saveTasks(finalTaskList);
            System.out.println("TXT file saved successfully!");
        } catch (Exception e) {
            String botMessage = "OOPS! There is something wrong: " + e.getMessage();
            System.out.println(botMessage);
        }

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
    private void handleUserInput(TaskList taskList) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), taskList));
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
    private String getResponse(String input, TaskList taskList) {
        try {
            Command c = Parser.parse(input);
            String botMessage = c.execute(taskList);
            return "Duke says: " + botMessage;
        } catch (Exception e) {
            String botMessage = e.getMessage();
            return "Duke says: " + botMessage;
        }
    }




//    private Scanner keyboard;
//    private Storage storage;
//    private Ui ui;


//    /**
//     * A constructor for Duke class
//     *
//     * @param filePath The file path that stores the task list.
//     * @param dirPath The directory path that stores the directory of the task list file.
//     */
//    public Duke(String filePath, String dirPath) {
//        keyboard = new Scanner(System.in);
//        ui = new Ui();
//        storage = new Storage(filePath, dirPath);
//
//    }
//
//    /**
//     * Starts function to run the program.
//     */
//    public void run() {
//        TaskList taskList = new TaskList();
//        // Read from storage
//        try {
//            taskList = storage.readTasks(taskList);
//        } catch (Exception e) {
//            ui.display("OOPS! There is something wrong: " + e.getMessage());
//        }
//
//        // In Execution
//        ui.welcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand(keyboard);
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.display(e.getMessage());
//            }
//        }
//
//        // Save to files
//        try {
//            storage.saveTasks(taskList);
//        } catch (Exception e) {
//            ui.display("OOPS! There is something wrong: " + e.getMessage());
//        }
//
//    }

//    /**
//     * The main function of the program.
//     */
//    public static void main(String[] args) {
//        //        String logo = " ____        _        \n"
//        //                + "|  _ \\ _   _| | _____ \n"
//        //                + "| | | | | | | |/ / _ \\\n"
//        //                + "| |_| | |_| |   <  __/\n"
//        //                + "|____/ \\__,_|_|\\_\\___|\n";
//        Duke duke = new Duke("./data/duke.txt", "./data");
//        duke.run();
//
//    }
}
