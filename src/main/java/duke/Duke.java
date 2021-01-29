package duke;

import duke.controller.Parser;
import duke.controller.Storage;
import duke.controller.TaskList;
import duke.controller.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    @FXML
    private TextArea screen;

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
        Ui ui = new Ui();
        screen.appendText(ui.initialize() + "\n");
        try {
            listOfTasks = storage.handleLoad();
        } catch (DukeException e) {
            System.out.println(e.toString());
            System.exit(1);
        }

        parser = new Parser(ui);

        submit.setDisable(true);
        input.textProperty().addListener((observable, oldValue, newValue) ->
                submit.setDisable(newValue.equals("")));
    }

    @FXML
    private void onSubmit() {
        String userInput = input.getText();
        try {
            screen.appendText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - "
                    + userInput + "\n");
            if (userInput.equals("list")) {
                screen.appendText(parser.handleList(listOfTasks));
            } else {
                // handle the commands with arguments
                int spaceIndex = userInput.indexOf(" ");
                int cutOffPoint = spaceIndex == -1 ? userInput.length() : spaceIndex;
                String command = userInput.substring(0, cutOffPoint);

                String textToAppend = "";
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
                case "bye":
                    System.exit(0);
                    break;
                default:
                    textToAppend = "I have no idea what that means, what do you want?\n";
                    break;
                }
                screen.appendText(textToAppend + "\n");
            }
            input.clear();
            storage.handleSave(listOfTasks);
        } catch (DukeException e) {
            screen.appendText(e.toString() + "\n");
        }
    }
}
