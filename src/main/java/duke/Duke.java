package duke;

import duke.controller.Parser;
import duke.controller.Storage;
import duke.controller.TaskList;
import duke.controller.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Duke {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox content;

    @FXML
    private TextField input;

    @FXML
    private Button submit;

    private Parser parser;

    private TaskList listOfTasks;

    private static final String FILENAME = "duke.csv";

    private final Storage storage = new Storage(FILENAME);

    @FXML
    private void initialize() {
        initializeScreen();
        initializeInputField();
    }

    private void initializeScreen() {
        Ui ui = new Ui();
        parser = new Parser(ui);

        scrollPane.setContent(content);

        scrollPane.setVvalue(1.0);
        content.setPrefHeight(Region.USE_COMPUTED_SIZE);

        content.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        content.getChildren().add(DialogBox.dukeDialogMaker(ui.initialize() + "\n", false));

        try {
            listOfTasks = storage.handleLoad();
        } catch (DukeException e) {
            System.out.println(e.toString());
            System.exit(1);
        }

        content.getChildren().add(DialogBox.dukeDialogMaker(
                "Also, in case you forgot, here are the tasks in the next week:\n" +
                        listOfTasks.remind(7).toString(), false));
    }

    private void initializeInputField() {
        submit.setDisable(true);
        input.textProperty().addListener((observable, oldValue, newValue) ->
                submit.setDisable(newValue.equals("")));
        input.setOnKeyPressed((ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER) && input.getText().length() > 0) {
                onSubmit();
            }
        });
    }

    @FXML
    private void onSubmit() {
        String userInput = input.getText();
        assert (userInput != null && userInput.length() > 0);
        content.getChildren().add(DialogBox.userDialogMaker(userInput));
        try {
            if (userInput.equals("list")) {
                content.getChildren().add(
                        DialogBox.dukeDialogMaker(
                                parser.handleList(listOfTasks), false));
            } else {
                // handle the commands with arguments
                int spaceIndex = userInput.indexOf(" ");
                int cutOffPoint = spaceIndex == -1 ? userInput.length() : spaceIndex;
                String command = userInput.substring(0, cutOffPoint);

                String textToAppend = "";
                boolean defaultCase = false;
                switch (command) {
                case "done":
                    textToAppend = parser.handleDone(userInput, listOfTasks);
                    break;
                case "todo":
                    textToAppend = parser.handleTodo(userInput, listOfTasks);
                    break;
                case "delete":
                    textToAppend = parser.handleDelete(userInput, listOfTasks);
                    break;
                case "deadline":
                case "event":
                    textToAppend = parser.handleTasksWithTime(command, userInput, listOfTasks);
                    break;
                case "find":
                    textToAppend = parser.handleFind(userInput, listOfTasks);
                    break;
                case "remind":
                    textToAppend = parser.handleRemind(userInput, listOfTasks);
                    break;
                case "bye":
                    System.exit(0);
                    break;
                default:
                    textToAppend = "I have no idea what that means, what do you want?\n";
                    defaultCase = true;
                    break;
                }
                content.getChildren().add(DialogBox.dukeDialogMaker(textToAppend, defaultCase));
            }
            storage.handleSave(listOfTasks);
        } catch (DukeException e) {
            content.getChildren().add(DialogBox.dukeDialogMaker(e.toString(), true));
        } finally {
            input.clear();
        }
    }
}
