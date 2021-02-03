package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 * The types of tasks the user can add are: todo, deadline, event.
 * The user can also delete, check as done, and list tasks.
 */
public class Duke extends Application {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke class.
     * Initializes Ui, Parser, Storage and TaskList objects.
     * @param filePath file path to file where user wants his task list saved and loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public Duke() {

    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Initializes a Duke object with specified file path and calls run method.
     * @param args input from user
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Processes user input and interacts with Ui and TaskList objects.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        boolean continueReading = true;
        while (continueReading) {
            String input = sc.nextLine();

            String[] inputArr = parser.getInputArr(input);
            String cmd = inputArr[0];

            switch(cmd) {
            case "bye":
                ui.exit();
                continueReading = false;
                break;
            case "list":
                ui.listAllTasks(tasks.list);
                break;
            case "done":
                int numDone = Integer.parseInt(inputArr[1]);
                Task task = tasks.list.get(numDone - 1);
                tasks.checkAsDone(task);
                ui.checkAsDoneMessage(task);
                break;
            case "delete":
                int numDelete = Integer.parseInt(inputArr[1]);
                Task deletedTask = tasks.deleteTask(numDelete);
                ui.deleteTaskMessage(tasks.list, deletedTask);
                break;
            case "find":
                String keyword = inputArr[1];
                ui.findTask(keyword, tasks.list);
                break;
            case "todo":
                try {
                    parser.isEmptyDescription(inputArr);
                    String details = inputArr[1];
                    Task todo = new Todo(details);
                    tasks.addTask(todo);
                    ui.addTaskMessage(tasks.list, todo);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.\n" + HORIZONTAL_RULE);
                }
                break;
            case "deadline":
                try {
                    parser.isEmptyDescription(inputArr);
                    String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                    String date = input.substring(input.indexOf("/") + 4);
                    Task deadline = new Deadline(details, date);
                    tasks.addTask(deadline);
                    ui.addTaskMessage(tasks.list, deadline);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.\n" + HORIZONTAL_RULE);
                }
                break;
            case "event":
                try {
                    parser.isEmptyDescription(inputArr);
                    String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                    String date = input.substring(input.indexOf("/") + 4);
                    Task event = new Event(details, date);
                    tasks.addTask(event);
                    ui.addTaskMessage(tasks.list, event);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.\n" + HORIZONTAL_RULE);
                }
                break;
            default:
                try {
                    throw new DukeException();
                } catch (DukeException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + HORIZONTAL_RULE);
                }
            }
        }
        try {
            storage.saveTasks(tasks.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

}
