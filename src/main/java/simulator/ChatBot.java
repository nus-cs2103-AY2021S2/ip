package simulator;

import java.io.IOException;

import java.util.ArrayList;

import exception.DukeException;

import task.*;

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
        assert tasklist.size() >= 0 : "size smaller than 0";
        return Ui.retrieveList(tasklist.size() == 0);
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
            }
            if (command.equals("done") || command.equals("delete")) {
                int index = Integer.parseInt(parsedInput.get(1));
                return command.equals("done") ? tasklist.completeTask(index)
                        : tasklist.deleteTask(index);
            } else {
                String description = parsedInput.get(1);
                String duration;
                switch (command) { case "find":
                    return tasklist.find(description);
                case "todo":
                    if (detectAnomalies(command, description, null)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Todo(description));
                case "deadline":
                    duration = parsedInput.get(2);
                    if (detectAnomalies(command, description, duration)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Deadline(description, duration));
                case "event":
                    duration = parsedInput.get(2);
                    if (detectAnomalies(command, description, duration)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Event(description, duration));
                case "bye" :
                    return this.save();
                default:
                    throw new DukeException("☹ OOPS!!! Incorrect input, please check!");
                }
            }
        } catch (DukeException err) {
            return err.getMessage();
        } catch (Exception err) {
            return "☹ OOPS!!! Incorrect input, please check!";
        }
    }

    /**
     * Detects whether the added task clashes with another task in the list by verifying the specified task
     * <code>type</code>, <code>description</code> and <code> duration</code>.
     * @param type type of task
     * @param description description of task
     * @param duration duration of task
     * @return true if clash is detected, otherwise false.
     */
    public boolean detectAnomalies(String type, String description, String duration) {
        if (type.equals("todo")) {
            return tasklist.compareTodo(description);
        } else {
            String[] dateAndTime = duration.split("\\s+");
            if (type.equals("deadline")) {
                return tasklist.compareDeadline(description, dateAndTime);
            }
            if (type.equals("event")){
                return tasklist.compareEvent(description, dateAndTime);
            }
            return false;
        }
    }
}
