import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Formatter;
import util.Ui;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.Parser.getArgMap;
import static util.Parser.getCommand;

public class Sweh extends Application {
    public static TaskList taskList = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        System.out.println(Ui.greeting());

        taskList.readFromDisk();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            System.out.println(Formatter.formatOut(respond(cmd)));
            if (cmd.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    public static String respond(String commandString) {
        String command = getCommand(commandString);
        HashMap<String, String> argMap = getArgMap(commandString);

        try {
            switch (command) {
                case "list":
                    return taskList.listTasks();
                case "done":
                    return taskList.markTaskDone(argMap);
                case "delete":
                    return taskList.deleteTask(argMap);
                case "todo":
                    return taskList.addTask(ToDo.newInstance(argMap));
                case "deadline":
                    return taskList.addTask(Deadline.newInstance(argMap));
                case "event":
                    return taskList.addTask(Event.newInstance(argMap));
                case "find":
                    return taskList.findTask(argMap);
                case "bye":
                    return "Bye. See ya again soon!";
                default:
                    return "Sorry, I didn't understand that";
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please input dates in the form YYYY-MM-DD";
        }
    }

    public static String echo(String input) {
        return input;
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

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
    }
}
