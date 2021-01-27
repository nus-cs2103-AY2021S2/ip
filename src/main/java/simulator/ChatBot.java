package simulator;

import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import simulator.Parser;
import simulator.Storage;
import task.*;

import ui.Ui;

public class ChatBot {
    private TaskList tasklist;
    private Storage storage;
    private Parser parser;

    public ChatBot() {
        Ui.printBox(Ui.WELCOME_MSG + Ui.LOGO);
        storage = new Storage();
        parser = new Parser();
        tasklist = new TaskList();
    }

    public void startUp() {
        tasklist = storage.load(tasklist);
        Ui.printBox(Ui.GREETING_MSG);
    }

    public void save() throws IOException {
        storage.save(tasklist);
    }

    public void process(String input) {
        ArrayList<String> parsedInput = parser.parseInput(input);
        try {
            String command = parsedInput.get(0);
            if (command.equals("list")) {
                Ui.printList(tasklist);
            } else {
                if (command.equals("done")) {
                    int index = Integer.parseInt(parsedInput.get(1));
                    tasklist.completeTask(index);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(parsedInput.get(1));
                    tasklist.deleteTask(index);
                } else {
                    String description = parsedInput.get(1);
                    Task newTask;
                    String duration;
                    switch (command) {
                        case "todo":
                            newTask = new Todo(description);
                            tasklist.addTask(newTask);
                            break;
                        case "deadline":
                            duration = parsedInput.get(2);
                            newTask = new Deadline(description, duration);
                            tasklist.addTask(newTask);
                            break;
                        case "event":
                            duration = parsedInput.get(2);
                            newTask = new Event(description, duration);
                            tasklist.addTask(newTask);
                            break;
                        default:
                            Ui.printBox("☹ OOPS!!! Incorrect input, please check!");
                            break;
                    }
                }
            }
        } catch (DukeException err) {
            Ui.printBox(err.getMessage());
        } catch (Exception err) {
            Ui.printBox("☹ OOPS!!! Incorrect input, please check!");
        }
    }

}
