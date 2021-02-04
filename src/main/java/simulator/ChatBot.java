package simulator;

import java.io.IOException;

import java.util.ArrayList;

import exception.DukeException;

import task.Event;
import task.TaskList;
import task.Deadline;
import task.Todo;

import ui.Ui;

/**
 * ChatBot class that contains the function of the chat bot.
 */
public class ChatBot {
    private TaskList tasklist;
    private final Storage storage;
    private final Parser parser;

    /**
     * Construct a ChatBot object.
     */
    public ChatBot() {
        storage = new Storage();
        parser = new Parser();
        tasklist = new TaskList();
    }

    /**
     * Startup the chat bot by loading the task list from storage.
     * @return null
     */
    public String startup() {
        tasklist = storage.load(tasklist);
        if (tasklist.size() == 0) {
            return Ui.formatBox(Ui.INDENT_32_SPACES + "No Save Record Detected... \n"
                    + Ui.INDENT_32_SPACES + "     Creating New List! :)");
        } else {
            return Ui.formatBox(Ui.INDENT_32_SPACES + "Saved Record Detected... \n"
                    + Ui.INDENT_32_SPACES + "     Retrieving List! :)");
        }
    }

    /**
     * Saves the current task list into the storage.
     * @return Save message
     * @throws IOException IOException
     */
    public String save() throws IOException {
       return storage.save(tasklist);
    }

    /**
     * Process the specified user <code>input</code>.
     * @param input input from user
     * @return A String from ChatBot
     */
    public String process(String input) {
        ArrayList<String> parsedInput = parser.parseInput(input);
        try {
            String command = parsedInput.get(0);
            if (command.equals("list")) {
                return Ui.printList(tasklist);
            } else {
                if (command.equals("done") || command.equals("delete")) {
                    int index = Integer.parseInt(parsedInput.get(1));
                    return command.equals("done") ? tasklist.completeTask(index) : tasklist.deleteTask(index);
                } else {
                    String description = parsedInput.get(1);
                    String duration;
                    switch (command) {
                    case "find":
                        return tasklist.find(description);
                    case "todo":
                        return tasklist.addTask(new Todo(description));
                    case "deadline":
                        duration = parsedInput.get(2);
                        return tasklist.addTask(new Deadline(description, duration));
                    case "event":
                        duration = parsedInput.get(2);
                        return tasklist.addTask(new Event(description, duration));
                    case "bye" :
                        return this.save();
                    default:
                        throw new DukeException("☹ OOPS!!! Incorrect input, please check!");
                    }
                }
            }
        } catch (DukeException err) {
            return err.getMessage();
        } catch (Exception err) {
            return "☹ OOPS!!! Incorrect input, please check!";
        }
    }
}
