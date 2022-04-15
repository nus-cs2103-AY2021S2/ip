package zeke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import zeke.exceptions.InvalidDateException;
import zeke.exceptions.UnknownInputException;
import zeke.exceptions.ZekeException;

/**
 * Zeke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Zeke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Zeke class.
     * Initializes Ui, Parser, Storage and TaskList objects.
     *
     * @param filePath file path to file where user wants his task list saved and loaded.
     */
    public Zeke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Processes user input and returns Zeke's response.
     *
     * @return response of Zeke to user input.
     */
    public String getResponse(String input) {
        String response;
        String description;
        String date;
        String[] inputArr = parser.getInputArr(input);
        try {
            Command command = parser.getCommand(inputArr);
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
        } catch (ZekeException | IOException e) {
            response = e.getMessage();
        } catch (DateTimeParseException e) {
            response = new InvalidDateException().getMessage();
        } catch (StringIndexOutOfBoundsException e) {
            response = new InvalidDateException().getMessage();
        } catch (Exception e) {
            response = new UnknownInputException().getMessage();
        }
        return response;
    }


}
