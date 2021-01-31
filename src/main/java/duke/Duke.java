package duke;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

/**
 * Duke class that simulates the running of the Duke Program
 */
public class Duke extends Application {

    /** Storage instance that is used by Duke during run for loading and writing of file*/
    private Storage storage;

    /** TaskList instance used by Duke during run that manages the tasks */
    private TaskList tasks;

    /** Ui instance used by Duke during run to interact with User */
    private Ui ui;

    private String filePath = "./data/tasks.txt";
    /**
     * Constructor for the Duke class
     */
    public Duke() {

        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());

        } catch (DukeException e) {

            ui.showLoadingError();
            tasks = new TaskList();

        }
    }

    /**
     * Runs the Duke Program. The Duke Program will watch for user input and react accordingly to the
     * user input in this method
     */
    public void run() {
        ui.printDivider();
        ui.printWelcome();
        ui.printDivider();

        try {
            boolean carryOn = true;

            while (carryOn) {

                String action = ui.read();

                Parser parser = new Parser(action);
                parser.check();

                String[] parsedAction = parser.getParsedAction();

                switch (parsedAction[0]) {
                case "todo":
                    ui.printDivider();
                    ui.addPrint();
                    ToDoTask todo = tasks.handleToDoTask(action);
                    ui.printTask(todo);
                    ui.countTasks(tasks);
                    ui.printDivider();

                    break;

                case "deadline":
                    ui.printDivider();

                    ui.addPrint();

                    DeadlineTask deadlineTask = tasks.handleDeadlineTask(action);

                    ui.printTask(deadlineTask);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "event":
                    ui.printDivider();

                    ui.addPrint();

                    EventTask eventTask = tasks.handleEventTask(action);

                    ui.printTask(eventTask);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "list":
                    ui.printDivider();

                    ui.printStored(tasks);

                    ui.printDivider();

                    break;

                case "done":
                    int number = Integer.valueOf(parsedAction[1]);
                    ui.printDivider();

                    ui.printMarked();

                    Task completed = tasks.handleDone(number);

                    ui.printTask(completed);

                    ui.printDivider();
                    break;

                case "check":
                    ui.printDivider();

                    String result = tasks.findOnDateTasks((parsedAction[1]));

                    ui.print(result);

                    ui.printDivider();

                    break;

                case "bye":
                    carryOn = false;

                    break;

                case "delete":
                    int index = Integer.valueOf(parsedAction[1]);

                    ui.printDivider();

                    ui.printRemoved();

                    Task task = tasks.handleDelete(index);

                    ui.printTask(task);
                    ui.countTasks(tasks);

                    ui.printDivider();
                    break;

                case "find":
                    String keyword = parsedAction[1];

                    ui.printDivider();
                    ui.printMatching();

                    List<Task> matches = tasks.getMatch(keyword);

                    ui.printList(matches);
                    ui.printDivider();

                    break;

                default:
                    throw new UnknownInputException();
                }
            }

            ui.printDivider();

            ui.printBye();

            ui.printDivider();

            storage.write(tasks);

        } catch (DukeException e) {
            ui.printDivider();

            ui.print(e.getMessage());

            ui.printDivider();
        }
        
    }

    /**
     * main driver method to run the Duke program
     * @param args variable arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
    
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
