package duke;

// JavaFX Imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class Duke extends Application{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Prints the greeting for the chatbot.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        dialogContainer.getChildren().add(getDialogLabel("Hello from\n" + logo));
    }

    /**
     *  This method will check to see if any events or deadlines are due or overdue within 3 days. It will
     *  an appropriate message
     * @param taskList The TaskList for the method to check
     */
    public void remind(TaskList taskList){

        HashMap<Task, Integer> taskMap = taskList.getDueTasks();
        int upcoming = 0;
        int overdue = 0;
        int today = 0;

        for (Task task : taskMap.keySet()) {
            int due = taskMap.get(task);

            if (due > 0) {
                upcoming += 1;
            } else if (due < 0) {
                overdue += 1;
            } else {
                today += 1;
            }
        }

        String updateString = "";

        if (today != 0) {
            String reminder = "You have " + today + " task(s) today:\n";

            for (Task task : taskMap.keySet()) {
                int due = taskMap.get(task);

                if (due == 0) {
                    reminder += task.toString() + "\n";
                }
            }

            updateString += reminder + "\n";
        }

        if (upcoming != 0) {
            String reminder = "You have " + upcoming + " upcoming task(s):\n";

            for (Task task : taskMap.keySet()) {
                int due = taskMap.get(task);

                if (due > 0) {
                    reminder += task.toString() + "\n";
                }
            }

            updateString += reminder + "\n";
        }

        if (upcoming != 0) {
            String reminder = "You have " + overdue + " overdue task(s):\nConsider removing these tasks\n";

            for (Task task : taskMap.keySet()) {
                int due = taskMap.get(task);

                if (due < 0) {
                    reminder += task.toString() + "\n";
                }
            }

            updateString += reminder + "\n";
        }

        dialogContainer.getChildren().add(getDialogLabel(updateString));

    }

    /**
     * This method is used to start the Duke user interface
     */
    @Override
    public void start(Stage stage) {
        Parser parser = new Parser();

        Storage storage = new Storage("tasklist");
        TaskList taskList = storage.loadTaskList();

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

        greet();
        remind(taskList);

        // Functionality
        sendButton.setOnMouseClicked((event) -> {
            String input = userInput.getText();

            HBox hBox = new HBox();
            hBox.getChildren().add(getDialogLabel(input + " <<"));
            hBox.setAlignment(Pos.BASELINE_RIGHT);

            dialogContainer.getChildren().add(hBox);
            userInput.clear();

            // Call Parser
            String out = parser.process(input, taskList);
            dialogContainer.getChildren().add(getDialogLabel(out));

            if (parser.checkEnd()) {

                exitProcedure(dialogContainer, taskList, storage);

            }
        });

        userInput.setOnAction((event) -> {
            String input = userInput.getText();

            HBox hBox = new HBox();
            hBox.getChildren().add(getDialogLabel(input + " <<"));
            hBox.setAlignment(Pos.BASELINE_RIGHT);

            dialogContainer.getChildren().add(hBox);
            userInput.clear();

            // Call Parser
            String out = parser.process(input, taskList);
            dialogContainer.getChildren().add(getDialogLabel(out));

            if (parser.checkEnd()) {

                exitProcedure(dialogContainer, taskList, storage);

            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private void exitProcedure(VBox dialogContainer, TaskList taskList, Storage storage) {
        try {
            storage.saveTaskList(taskList);
        } catch(DukeException e) {
            dialogContainer.getChildren().add(getDialogLabel(e.toString()));
        }

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

    /**
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
