package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Duke is a program for task tracking.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath a path representing the location of duke save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Runner for duke.
     * Read, process input and will output accordingly.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String request = "";

        while (!request.equals("bye")) {
            Parser parser = new Parser(sc.nextLine());
            request = parser.getRequest();
            String args = parser.getArgs();

            if (request.equals("bye")) {
                ui.showBye();
                break;
            } else if (request.equals("list")) {
                ui.printList(tasks);
            } else if (request.equals("done")) {
                try {
                    int taskNo = Integer.parseInt(args);
                    ui.printMarked(tasks.markDone(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("delete")) {
                try {
                    int taskNo = Integer.parseInt(args);
                    ui.printRemoved(tasks, tasks.removeTask(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("todo")) {
                try {
                    Task task = Task.createTask(args, request, "", "");
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("deadline")) {
                try {
                    String[] deadStr = parser.getFormattedCommand();
                    Task task = Task.createTask(deadStr[0], request, deadStr[1], deadStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("event")) {
                try {
                    String[] eventStr = parser.getFormattedCommand();
                    Task task = Task.createTask(eventStr[0], request, eventStr[1], eventStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("find")) {
                ui.printFound(tasks.findTask(args));
            } else {
                try {
                    throwDontKnowException();
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Throw DukeException with fixed message.
     * This method will always throw a DukeException with a fixed message. If
     * user input is invalid, this method can be used to throw an exception.
     *
     * @throws DukeException
     */
    public static void throwDontKnowException() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
