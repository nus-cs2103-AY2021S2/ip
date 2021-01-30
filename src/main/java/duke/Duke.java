package duke;

import duke.exceptions.*;
import duke.task.Task;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.Scanner;

/**
 * Main class of the <code>Duke</code> application.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static boolean isExit = false;
    private final String filePath;

    /**
     * Constructor for Duke application.
     * Initializes Ui, Storage and TaskList.
     *
     * @param filePath Filepath of the file that saves tasks.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readFileContents(filePath));
        } catch (FileNotFoundException | InvalidFolderException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes Duke application until user terminates it.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.welcome();

        while (!isExit) {
            try {
                String userInput = sc.nextLine();
                Parser parser = new Parser(userInput);
                String[] parsedUserInput = parser.parseUserInput();
                parser.checkUserInput(tasks.list);
                String userAction = parser.getUserAction();

                switch (userAction) {
                case "bye":
                    ui.exit();
                    isExit = true;
                    break;

                case "list":
                    ui.printTaskList(tasks.list);
                    break;

                case "todo":
                case "deadline":
                case "event":
                    Task newTask = tasks.addNewTask(userInput);
                    ui.printAddTask(newTask, tasks.list);
                    break;

                case "done":
                    tasks.markAsDone(userInput);
                    ui.printDoneTask(Integer.parseInt(parsedUserInput[1]) - 1, tasks.list);
                    break;

                case "delete":
                    ui.printDeleteTask(Integer.parseInt(parsedUserInput[1]) - 1, tasks.list);
                    tasks.deleteTask(userInput);
                    break;

                case "find":
                    List<Task> tempList = tasks.findTask(userInput);
                    ui.printFindKeyword(tempList);
                    break;

                default:
                    throw new InvalidInputException();
                }
                storage.overWriteFile(filePath, tasks.list);
            } catch (DukeException ex) {
                ui.printDivider();
                System.out.println(ex.getMessage());
                ui.printDivider();
            }
        }
        sc.close();
    }

    /**
     * Main method for Duke application.
     * @param args Argument fed into the command line.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
