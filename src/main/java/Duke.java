import java.io.IOException;
import java.time.format.DateTimeParseException;
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

public class Duke extends Application {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for the Duke class.
     *
     * @throws IOException If an error occurs while reading from the file.
     */
    public Duke() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(DIR_NAME, FILE_NAME);
//
//        storage.readFromFile(tasks, ui);
//        ui.greet();
//
//        Scanner sc = new Scanner(System.in);
//        while (readInput(sc)) ;
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private int getIndex(String args) {
        return Integer.parseInt(args) - 1;
    }

    /**
     * Adds a new Todo to the TaskList.
     *
     * @param command Command with details about the Todo to be created.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void addTodo(Command command) throws IOException {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        storage.writeToFile(todo);
        ui.addedTaskReply(todo, tasks.size());
    }

    /**
     * Adds a new Deadline to the TaskList
     *
     * @param command Command with details about the Deadline to be created.
     * @throws IOException If an error occurs while writing to the file.
     * @throws DateTimeParseException If date provided is not of the "dd/mm/yyyy hhmm" or "dd/mm/yyyy" format.
     */
    public void addDeadline(Command command) throws IOException, DateTimeParseException {
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            storage.writeToFile(deadline);
            ui.addedTaskReply(deadline, tasks.size());
        }
    }

    /**
     * Adds a new Event to the TaskList
     *
     * @param command Command with details about the Event to be created.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void addEvent(Command command) throws IOException {
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            storage.writeToFile(event);
            ui.addedTaskReply(event, tasks.size());
        }
    }

    /**
     * Marks a task as done
     *
     * @param command Command containing the index of the task to mark as done.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void done(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            tasks.getTaskAt(index).markDone();
            storage.updateFile(tasks);
            ui.markDoneReply(tasks.getTaskAt(index));
        }
    }

    /**
     * Deletes a task
     *
     * @param command Command containing the index of the task to delete.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void delete(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            storage.updateFile(tasks);
            ui.deleteReply(deletedTask, tasks.size());
        }
    }

    /**
     * Finds and lists tasks matching the given keyword
     *
     * @param command Command containing the keyword to search for
     */
    public void find(Command command) {
        String keyword = command.getArgs();

        TaskList matchingTasks = tasks.findMatchingTasks(keyword);
        ui.listMatchingTasks(matchingTasks);
    }

    /**
     * Processes user's input and executes the respective command
     *
     * @param sc Scanner object for reading user's input
     * @return False if the program should terminate, else true.
     */
    public boolean readInput(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            Command command = Parser.parseInput(input);

            switch (command.getInstruction()) {
            case Command.BYE:
                ui.exit();
                return false;
            case Command.LIST:
                ui.list(tasks);
                break;
            case Command.DONE:
                done(command);
                break;
            case Command.DELETE:
                delete(command);
                break;
            case Command.FIND:
                find(command);
                break;
            case Command.TODO:
                addTodo(command);
                break;
            case Command.EVENT:
                addEvent(command);
                break;
            case Command.DEADLINE:
                addDeadline(command);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyDescriptionException | InvalidTaskException | TaskNotFoundException | IOException exception) {
            ui.reply(ui.formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            ui.reply(ui.formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }

        return true;
    }

//    public static void main(String[] args) throws IOException {
//        new Duke();
//    }
}
