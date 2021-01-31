import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;

    private TaskList tasks;

    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));


    String name;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
        name = "Link";
    }


    @Override
    public void start(Stage stage) throws Exception {
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

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
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
        dialogContainer.setPrefHeight(  Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input, String[] parts) throws InsufficientArgumentsException, FileNotFoundException {
        int listCounter = 1;
        switch (input) {
        case "done": {
            int index = Integer.parseInt(parts[1]);
            Task toMark = tasks.get(index - 1);
            toMark.markAsDone();
            TaskList.getTasklist().get(index - 1).markAsDone();
            storage.save();
            return toMark.toString();
        }
        case "todo":
        case "deadline":
        case "event":
            if (parts.length == 1) {
                throw new InsufficientArgumentsException("Insufficient arguments provided");
            }
            StringBuilder str = new StringBuilder();
            if (input.equals("todo")) {
                for (int i = 1; i < parts.length; i++) {
                    str.append(" ");
                    str.append(parts[i]);
                }
                String taskString = str.toString();
                Todo todo = new Todo(taskString);
                tasks.add(todo);
                storage.save();
                return todo.toString();
            } else {
                int slashIndex = 0;
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].contains(Character.toString('/'))) {
                        slashIndex = i;
                    }
                }
                for (int j = 1; j < slashIndex; j++) {
                    str.append(" ");
                    str.append(parts[j]);
                }
                String taskString = str.toString();
                StringBuilder byStringBuilder = new StringBuilder();
                for (int k = slashIndex + 1; k < parts.length; k++) {
                    if (k != slashIndex + 1) {
                        byStringBuilder.append(" ");
                    }
                    byStringBuilder.append(parts[k]);
                }
                String DateString = byStringBuilder.toString();
                LocalDate date = LocalDate.parse(DateString);
                if (input.equals("deadline")) {
                    Deadline deadline = new Deadline(taskString, date);
                    tasks.add(deadline);
                    storage.save();
                    return deadline.toString();
                } else {
                    Event event = new Event(taskString, date);
                    tasks.add(event);
                    storage.save();
                    return event.toString();
                }
            }

        case "delete": {
            if (parts.length > 2) {
                throw new InsufficientArgumentsException("Wrong arguments");
            }
            int index = Integer.parseInt(parts[1]);
            Task toBeRemoved = tasks.get(index - 1);
            tasks.remove(index - 1);
            storage.save();
            return "Noted. I've removed this task:\n" + toBeRemoved.toString();
        }
        case "list": {
            StringBuilder listStringBuilder = new StringBuilder();
            for (Task t : TaskList.getTasklist()) {
                listStringBuilder.append(listCounter).append(".").append(t.toString());
                listStringBuilder.append("\n");
                listCounter++;
            }
            storage.save();
            return listStringBuilder.toString();
        }
        case "find": {
            if (parts.length == 1) {
                throw new InsufficientArgumentsException("Insufficient arguments provided");
            }
            String greetingMessage = "Here are the matching tasks in your list";
            StringBuilder keyString = new StringBuilder();
            StringBuilder findStringBuilder = new StringBuilder();
            findStringBuilder.append(greetingMessage).append("\n");
            for (int i = 1; i < parts.length; i++) {
                keyString.append(" ");
                keyString.append(parts[i]);
            }
            String findString = keyString.toString();
            for (Task t: TaskList.getTasklist()) {
                if (t.toString().contains(findString)) {
                    findStringBuilder.append(t.toString()).append("\n");
                }
            }
            storage.save();
            return findStringBuilder.toString();
        }
        }
        return "duke says hello";
    }

}