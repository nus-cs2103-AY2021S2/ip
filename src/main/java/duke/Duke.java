package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a program for task tracking.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

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
     * Throw DukeException with fixed message.
     * This method will always throw a DukeException with a fixed message. If
     * user input is invalid, this method can be used to throw an exception.
     *
     * @throws DukeException
     */
    public static void throwDontKnowException() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String getResponse(String input) {
        String request = input;
        Parser parser = new Parser(request);
        request = parser.getRequest();
        String args = parser.getArgs();
        String message = "";

        if (request.equals("bye")) {
            message = ui.getByeMessage();
        } else if (request.equals("list")) {
            message = ui.getListMessage(tasks);
        } else if (request.equals("done")) {
            try {
                int taskNo = Integer.parseInt(args);
                message = ui.getMarkMessage(tasks.markDone(taskNo));
                storage.save(tasks);
            } catch (DukeException ex) {
                message = ex.getMessage();
            } catch (NumberFormatException ex) {
                message = "Please enter integer values..";
            }
        } else if (request.equals("delete")) {
            try {
                int taskNo = Integer.parseInt(args);
                message = ui.getRemoveMessage(tasks, tasks.removeTask(taskNo));
                storage.save(tasks);
            } catch (DukeException ex) {
                message = ex.getMessage();
            } catch (NumberFormatException ex) {
                message = "Please enter integer values..";
            }
        } else if (request.equals("todo")) {
            try {
                Task task = Task.createTask(args, request, "", "");
                message = ui.getAddMessage(tasks, tasks.addTask(task));
                storage.save(tasks);
            } catch (DukeException ex) {
                message = ex.getMessage();
            }
        } else if (request.equals("deadline")) {
            try {
                String[] deadStr = parser.getFormattedCommand();
                Task task = Task.createTask(deadStr[0], request, deadStr[1], deadStr[2]);
                message = ui.getAddMessage(tasks, tasks.addTask(task));
                storage.save(tasks);
            } catch (DukeException ex) {
                message = ex.getMessage();
            }
        } else if (request.equals("event")) {
            try {
                String[] eventStr = parser.getFormattedCommand();
                Task task = Task.createTask(eventStr[0], request, eventStr[1], eventStr[2]);
                message = ui.getAddMessage(tasks, tasks.addTask(task));
                storage.save(tasks);
            } catch (DukeException ex) {
                message = ex.getMessage();
            }
        } else if (request.equals("find")) {
            message = ui.getFoundMessage(tasks.findTask(args));
        } else {
            try {
                throwDontKnowException();
            } catch (DukeException ex) {
                message = ex.getMessage();
            }
        }
        return message;
    }

}
