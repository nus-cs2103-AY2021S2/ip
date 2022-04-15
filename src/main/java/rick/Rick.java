package rick;

import rick.exceptions.RickException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * <h1>Duke</h1>
 * The Duke program implements an application that is able to
 * read user inputs to record and save user's tasks.
 *
 * @author A0200357R Sean Iau Yang
 */
public class Rick {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Rick(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath, ui);
            tasks = new TaskList(storage.load());
        } catch(IOException error) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Command command = parser.parseCommand(input);
            switch(command) {
                case BYE:
                    response = Gui.getGoodbyeString();
                    storage.save(tasks);
                    break;
                case LIST:
                    response = Gui.getTasksString(tasks);
                    break;
                case DONE:
                    int taskToMarkAsDone = parser.parseDoneCommand(input);
                    response = Gui.getMessageString("Good work, Morty! I've marked this task as done:\n  " + tasks.markTaskAsDone(taskToMarkAsDone));
                    storage.save(tasks);
                    break;
                case DELETE:
                    int taskToDelete = parser.parseDeleteCommand(input);
                    response = Gui.getMessageString("Got it. I've removed this task:\n  " + tasks.deleteTask(taskToDelete) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case TODO:
                    Todo newTodo = parser.parseTodoCommand(input);
                    tasks.addTask(newTodo);
                    response = Gui.getMessageString("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case DEADLINE:
                    Deadline newDeadline = parser.parseDeadlineCommand(input);
                    tasks.addTask(newDeadline);
                    response = Gui.getMessageString("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case EVENT:
                    Event newEvent = parser.parseEventCommand(input);
                    tasks.addTask(newEvent);
                    response = Gui.getMessageString("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case FIND:
                    String keywords = parser.parseFindCommand(input);
                    TaskList foundTasks = tasks.findTasks(keywords);
                    response = Gui.getFoundTasksString(foundTasks);
                    break;
                case HELP:
                    response = Gui.getHelpMessageString();
                    break;
                default:
                    assert false : command;
                    break;
            }
        } catch(RickException error) {
            response = Gui.getErrorMessageString(error.getMessage());
        } catch(DateTimeParseException error) {
            response = Gui.getErrorMessageString("The date provided is invalid.\nValid format: yyyy-mm-dd");
        } catch (IndexOutOfBoundsException error) {
            response = Gui.getErrorMessageString("Selected item does not exist.");
        } catch (NumberFormatException error) {
            response = Gui.getErrorMessageString("Invalid task index number.");
        } catch (IOException error) {
            response = Gui.getSavingErrorString();
        }
        return response;
    }
}
