package duke;

import java.io.FileNotFoundException;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFolderException;
import duke.exceptions.InvalidInputException;
import duke.task.Task;

/**
 * <code>Duke</code> class to support the GUI for Duke.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static final String filePath = "./data/duke.txt";

    /**
     * Constructor for Duke class.
     * Initializes Ui, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readFileContents(filePath));
        } catch (FileNotFoundException | InvalidFolderException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates responses to user input.
     *
     * @return Response generated according to the task action.
     */
    public String getResponse(String userInput) {
        StringBuilder response = new StringBuilder();

        try {
            Parser parser = new Parser(userInput);
            String[] parsedUserInput = parser.parseUserInput();
            parser.checkUserInput(tasks.list);
            String userAction = parser.getUserAction();

            switch (userAction) {
            case "bye":
                response.append(ui.exit());
                break;

            case "list":
                response.append(ui.printTaskList(tasks.list));
                break;

            case "todo":
            case "deadline":
            case "event":
                Task newTask = tasks.addNewTask(userInput);
                response.append(ui.printAddTask(newTask, tasks.list));
                break;

            case "done":
                tasks.markAsDone(userInput);
                response.append(ui.printDoneTask(Integer
                                .parseInt(parsedUserInput[1]) - 1,
                        tasks.list));
                break;

            case "delete":
                response.append(ui.printDeleteTask(Integer
                                .parseInt(parsedUserInput[1]) - 1,
                        tasks.list));
                tasks.deleteTask(userInput);
                break;

            case "find":
                List<Task> tempList = tasks.findTask(userInput);
                response.append(ui.printFindKeyword(tempList));
                break;

            default:
                throw new InvalidInputException();
            }
            storage.overWriteFile(filePath, tasks.list);
        } catch (DukeException ex) {
            response.append(ex.getMessage());
        }
        return response.toString();
    }
}
