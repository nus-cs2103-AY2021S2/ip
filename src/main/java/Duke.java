import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {

    /** List of tasks added by the user */
    private static final List<Task> tasks = Storage.getData();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Liz.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Zee.jpg"));

    /**
     * Performs the specified action.
     *
     * @param command Command input by the user.
     * @throws InvalidCommandException If the command cannot be recognised.
     */
    public static String runCommand(String command) throws InvalidCommandException {
        String reply;
        if (command.equals("list")) {
            reply = TaskList.printList(tasks);
        } else if (Parser.parseCommand(command).equals("done")) {
            int index = Parser.parseDoneIndex(command);
            reply = TaskList.markDone(index, tasks);
        } else if (Parser.parseCommand(command).equals("todo")) {
            try {
                reply = TaskList.addTodo(command, tasks);
            } catch (InvalidTodoException e) {
                reply = Ui.printEmptyTodoMessage();
            }
        } else if (Parser.parseCommand(command).equals("deadline")) {
            try {
                reply = TaskList.addDeadline(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.printInvalidDateFormatMessage();
            }
        } else if (Parser.parseCommand(command).equals("event")) {
            try {
                reply = TaskList.addEvent(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.printInvalidDateFormatMessage();
            }
        } else if (Parser.parseCommand(command).equals("delete")) {
            reply = TaskList.deleteTask(command, tasks);
        } else if (Parser.parseCommand(command).equals("find")) {
            reply = TaskList.findTask(command, tasks);
        } else if(command.equals("bye")) {
            reply = Ui.printExitMessage();
            System.exit(0);
        } else {
            throw new InvalidCommandException();
        }
        return reply;
    }


    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            try {
                runCommand(command);
            } catch (InvalidCommandException e) {
                Ui.printInvalidCommandMessage();
            }
            command = scanner.nextLine();
        }
        scanner.close();
        Ui.printExitMessage();
    }

    public String getResponse(String input) {
        String reply;
        try {
            reply = runCommand(input);
        } catch (InvalidCommandException e) {
            reply = Ui.printInvalidCommandMessage();
        }
        return reply;
    }


}