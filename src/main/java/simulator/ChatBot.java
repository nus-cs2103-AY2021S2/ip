package simulator;

import java.io.IOException;

import java.util.ArrayList;

import exception.DukeException;

import task.Event;
import task.TaskList;
import task.Deadline;
import task.Todo;
import task.Task;

import ui.Ui;

import static ui.Ui.printBox;

public class ChatBot {
    private TaskList tasklist;
    private Storage storage;
    private Parser parser;

    public ChatBot() {
        storage = new Storage();
        parser = new Parser();
        tasklist = new TaskList();

    }

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

    public String save() throws IOException {
       return storage.save(tasklist);
    }

    public String process(String input) {
        ArrayList<String> parsedInput = parser.parseInput(input);
        try {
            String command = parsedInput.get(0);
            if (command.equals("list")) {
                return Ui.printList(tasklist);
            } else {
                if (command.equals("done")) {
                    int index = Integer.parseInt(parsedInput.get(1));
                    return tasklist.completeTask(index);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(parsedInput.get(1));
                    return tasklist.deleteTask(index);
                } else {
                    String description = parsedInput.get(1);
                    Task newTask;
                    String duration;
                    switch (command) {
                    case "todo":
                        newTask = new Todo(description);
                        return tasklist.addTask(newTask);
                    case "deadline":
                        duration = parsedInput.get(2);
                        newTask = new Deadline(description, duration);
                        return tasklist.addTask(newTask);
                    case "event":
                        duration = parsedInput.get(2);
                        newTask = new Event(description, duration);
                        return tasklist.addTask(newTask);
                    case "bye" :
                        try {
                            return this.save();
                        } catch (IOException ex) {
                            return ex.getMessage();
                        }
                    default:
                        return "☹ OOPS!!! Incorrect input, please check!";
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
