package duke;

import java.io.FileNotFoundException;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFileDataException;
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
    private static Parser parser;

    /**
     * Constructor for Duke class.
     * Initializes Ui, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage();
            tasks = new TaskList(storage.readFileContents());
        } catch (FileNotFoundException | InvalidFolderException
                | InvalidFileDataException e) {
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
        String[] parsedUserInput = parser.parseUserInput(userInput);

        try {
            parser.checkUserInput(tasks.list, userInput);
            String userAction = parsedUserInput[0];

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

            case "update":
                int taskToUpdate = Integer.parseInt(parsedUserInput[1]) - 1;
                String detailToUpdate = parsedUserInput[2];
                String newDetail = parsedUserInput[3];

                if (detailToUpdate.equals("date/time")) {
                    newDetail = newDetail + " " + parsedUserInput[4];
                }

                tasks.updateTask(taskToUpdate, detailToUpdate, newDetail);
                response.append(ui.printUpdatePrompt(tasks.list, taskToUpdate));
                break;

            default:
                throw new InvalidInputException();
            }
            storage.overWriteFile(tasks.list);
        } catch (DukeException ex) {
            response.append(ex.getMessage());
        }
        return response.toString();
    }
}
