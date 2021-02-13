package duke;

import java.io.IOException;
import java.util.Locale;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 * The types of tasks the user can add are: todo, deadline, event.
 * The user can also delete, check as done, and list tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke class.
     * Initializes Ui, Parser, Storage and TaskList objects.
     *
     * @param filePath file path to file where user wants his task list saved and loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Processes user input and interacts with Ui and TaskList objects.
     */
    public String getResponse(String input) {
        String response;
        String[] inputArr = parser.getInputArr(input);
        try {
            Command command = Command.valueOf(inputArr[0].toUpperCase(Locale.ROOT));
            String description;
            String date;
            switch (command) {
            case BYE:
                response = ui.exit();
                break;
            case LIST:
                response = ui.listAllTasks(tasks.getList());
                break;
            case DONE:
                parser.isValidIndex(inputArr, tasks.getList());
                int numDone = Integer.parseInt(inputArr[1]);
                Task task = tasks.getList().get(numDone - 1);
                tasks.checkAsDone(task);
                response = ui.checkAsDoneMessage(task);
                break;
            case DELETE:
                parser.isValidIndex(inputArr, tasks.getList());
                int numDelete = Integer.parseInt(inputArr[1]);
                Task deletedTask = tasks.deleteTask(numDelete);
                response = ui.deleteTaskMessage(tasks.getList(), deletedTask);
                break;
            case FIND:
                String keyword = inputArr[1];
                response = ui.findTask(keyword, tasks.getList());
                break;
            case HELP:
                response = ui.viewHelpMessage();
                break;
            case TODO:
                parser.isValidDescription(inputArr);
                description = parser.getDescription(inputArr, "todo");
                Task todo = new Todo(description);
                tasks.addTask(todo);
                response = ui.addTaskMessage(tasks.getList(), todo);
                break;
            case DEADLINE:
                parser.isValidDescription(inputArr);
                description = parser.getDescription(inputArr, "deadline");
                date = parser.getDate(inputArr, "deadline");
                Task deadline = new Deadline(description, date);
                tasks.addTask(deadline);
                response = ui.addTaskMessage(tasks.getList(), deadline);
                break;
            case EVENT:
                parser.isValidDescription(inputArr);
                description = parser.getDescription(inputArr, "event");
                date = parser.getDate(inputArr, "event");
                Task event = new Event(description, date);
                tasks.addTask(event);
                response = ui.addTaskMessage(tasks.getList(), event);
                break;
            case STATS:
                response = ui.getStatistics(tasks.getList());
                break;
            default:
                throw new UnknownInputException();
            }
            storage.saveTasks(tasks.getList());
        } catch (IllegalArgumentException e) {
            response = new UnknownInputException().getMessage();
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        return response;
    }


}
