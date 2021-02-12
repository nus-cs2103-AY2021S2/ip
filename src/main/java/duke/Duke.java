package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * <h1>Duke</h1>
 * The Duke program implements an application that is able to
 * read user inputs to record and save user's tasks.
 *
 * @author A0200357R Sean Iau Yang
 * @version A-JavaDoc
 */
public class Duke {
    public static final String filePath = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.load());
        } catch(DukeException error) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        boolean isRunning = true;

        while(isRunning) {
            String input = sc.nextLine();
            try {
                Command command = parser.parseCommand(input);
                switch(command) {
                    case BYE:
                        ui.showGoodbye();
                        storage.save(tasks);
                        sc.close();
                        isRunning = false;
                        break;
                    case LIST:
                        ui.showTasks(tasks);
                        break;
                    case DONE:
                        int taskToMarkAsDone = parser.parseDoneCommand(input);
                        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.markTaskAsDone(taskToMarkAsDone));
                        storage.save(tasks);
                        break;
                    case DELETE:
                        int taskToDelete = parser.parseDeleteCommand(input);
                        ui.showMessage("Noted. I've removed this task:\n  " + tasks.deleteTask(taskToDelete) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case TODO:
                        Todo newTodo = parser.parseTodoCommand(input);
                        tasks.addTask(newTodo);
                        ui.showMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case DEADLINE:
                        try {
                            Deadline curr = parser.parseDeadlineCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of a deadline cannot be empty.");
                        }
                        break;
                    case EVENT:
                        try {
                            Event curr = parser.parseEventCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of an event cannot be empty.");
                        }
                        break;
                    case FIND:
                        try {
                            String keywords = parser.parseFindCommand(input);
                            TaskList foundTasks = tasks.findTasks(keywords);
                            ui.showFoundTasks(foundTasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("No task with specified keywords can be found.");
                        }
                        break;
                    case HELP:
                        ui.showHelpMessage();
                        break;
                }
            } catch(DukeException error) {
                ui.showInputError();
            } catch(DateTimeParseException error) {
                ui.showErrorMessage("The date provided is invalid.");
            } catch (IndexOutOfBoundsException error) {
                ui.showErrorMessage("Selected item does not exist.");
            }
        }
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Command command = parser.parseCommand(input);
            switch(command) {
                case BYE:
                    response += Gui.getGoodbyeString();
                    storage.save(tasks);
                    break;
                case LIST:
                    response += Gui.getTasksString(tasks);
                    break;
                case DONE:
                    int taskToMarkAsDone = parser.parseDoneCommand(input);
                    response += Gui.getMessageString("Good work, Morty! I've marked this task as done:\n  " + tasks.markTaskAsDone(taskToMarkAsDone));
                    storage.save(tasks);
                    break;
                case DELETE:
                    int taskToDelete = parser.parseDeleteCommand(input);
                    response += Gui.getMessageString("Got it. I've removed this task:\n  " + tasks.deleteTask(taskToDelete) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case TODO:
                    Todo newTodo = parser.parseTodoCommand(input);
                    tasks.addTask(newTodo);
                    response += Gui.getMessageString("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                    storage.save(tasks);
                    break;
                case DEADLINE:
                    try {
                        Deadline curr = parser.parseDeadlineCommand(input);
                        tasks.addTask(curr);
                        response += Gui.getMessageString("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                    } catch (DukeException error) {
                        response += Gui.getErrorMessageString("The description of a deadline cannot be empty.");
                    }
                    break;
                case EVENT:
                    try {
                        Event curr = parser.parseEventCommand(input);
                        tasks.addTask(curr);
                        response += Gui.getMessageString("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                    } catch (DukeException error) {
                        response += Gui.getErrorMessageString("The description of an event cannot be empty.");
                    }
                    break;
                case FIND:
                    try {
                        String keywords = parser.parseFindCommand(input);
                        TaskList foundTasks = tasks.findTasks(keywords);
                        response += Gui.getFoundTasksString(foundTasks);
                    } catch (DukeException error) {
                        response += Gui.getErrorMessageString("No task with specified keywords can be found.");
                    }
                    break;
                case HELP:
                    response += Gui.getHelpMessageString();
                    break;
                default:
                    response += Gui.getMessageString("sorry");
                    break;
            }
        } catch(DukeException error) {
            response += Gui.getInputErrorString();
        } catch(DateTimeParseException error) {
            response += Gui.getErrorMessageString("The date provided is invalid.");
        } catch (IndexOutOfBoundsException error) {
            response += Gui.getErrorMessageString("Selected item does not exist.");
        }
        return response;
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
