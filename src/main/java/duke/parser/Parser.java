package duke.parser;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Parser is the class that parses raw user commands and executes the intended effect
 */
public class Parser {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructor for the Parser class
     * @param ui Ui object that interacts with user
     * @param storage Storage object that handles saving/loading of tasks to and from hard disk
     * @param tasks TaskList object which contains all the tasks in the program
     */
    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Parses the raw command from the user, deciphers the intent and executes it. Adds/deletes/modifies tasks in
     * <code>task</code> and displays messages via <code>ui</code>. Displays error message in the event
     * that the command is not recognizable.
     * @param fullCommand raw command provided as a String
     * @return true if the user enters "bye", a sign to terminate the program
     */
    public boolean parse(String fullCommand) {
        try {
            StringBuilder response = new StringBuilder();
            if (fullCommand.equals("bye")) {
                response.append("Bye. Hope to see you soon!");
                this.ui.displayMessage(response.toString());
                return true;
            } else if (fullCommand.equals("list")) {
                response.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    response.append(i + 1);
                    response.append(".");
                    response.append(task);
                    if (i != tasks.size() - 1) {
                        response.append("\n");
                    }
                }
            } else if (fullCommand.startsWith("done")) {
                int userChoice = Integer.parseInt(fullCommand.split(" ")[1]);
                if (userChoice > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                }
                Task task = tasks.get(userChoice - 1);
                task.markComplete();
                response.append("Nice! I've marked this task as done:\n");
                response.append(task);
            } else if (fullCommand.startsWith("delete")) {
                int userChoice = Integer.parseInt(fullCommand.split(" ")[1]);
                if (userChoice > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                }
                Task task = tasks.remove(userChoice - 1);
                response.append("Noted. I've removed this task:\n  ");
                response.append(task);
                response.append("\nNow you have ");
                response.append(tasks.size());
                response.append(" duke.tasks in the list.");
            } else if (fullCommand.startsWith("event") ||
                    fullCommand.startsWith("todo") ||
                    fullCommand.startsWith("deadline")) {
                Task task = Task.parseTask(fullCommand);
                tasks.add(task);
                response.append("Got it. I've added this task:\n  added: ");
                response.append(task);
                response.append("\nNow you have ");
                response.append(tasks.size());
                response.append(" duke.tasks in the list.");
            } else if (fullCommand.startsWith("on")) {
                // TODO: Implement a command that fetches all deadlines on a given date
            } else if (fullCommand.startsWith("find")) {
                String query = fullCommand.split(" ", 2)[1];
                response.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (task.contains(query)) {
                        response.append(i + 1);
                        response.append(".");
                        response.append(task);
                        if (i != tasks.size() - 1) {
                            response.append("\n");
                        }
                    }
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            this.ui.displayMessage(response.toString());
        } catch (DukeException e) {
            this.ui.displayMessage(e.getMessage());
        }
        return false;
    }
}
