import Exceptions.*;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static boolean exit = false;
    private final String filePath;


    public Duke(String filePath) throws InvalidFolderException {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.readFileContents(filePath));
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.welcome();

        while (!exit) {
            try {
                String userInput = sc.nextLine();
                Parser parser = new Parser(userInput);
                String[] parsedUserInput = parser.parseUserInput();
                parser.checkUserInput(tasks.list);
                String userAction = parser.getUserAction();

                switch (userAction) {
                case "bye":  //exit
                    ui.exit();
                    exit = true;
                    break;

                case "list":  //list task
                    ui.printTaskList(tasks.list);
                    break;

                case "todo":
                case "deadline":
                case "event":  //add new task
                    Task newTask = tasks.addNewTask(userInput);
                    ui.printAddTask(newTask, tasks.list);
                    break;

                case "done":  //mark as done
                    tasks.markAsDone(userInput);
                    ui.printDoneTask(Integer.parseInt(parsedUserInput[1]) - 1, tasks.list);
                    break;

                case "delete":  //delete task
                    ui.printDeleteTask(Integer.parseInt(parsedUserInput[1]) - 1, tasks.list);
                    tasks.deleteTask(userInput);
                    break;

                default:
                    throw new InvalidInputException();
                }

                storage.overWriteFile(filePath, tasks.list);
            } catch (EmptyDescriptionException | InvalidInputException
                    | InvalidTaskNumberException | DateTimeException
                    | MissingTaskNumberException | InvalidDateException ex) {
                ui.printDivider();
                System.out.println(ex.getMessage());
                ui.printDivider();
            }
        }

        sc.close();
    }

    public static void main(String[] args) throws InvalidFolderException {
        new Duke("./data/duke.txt").run();
    }
}
