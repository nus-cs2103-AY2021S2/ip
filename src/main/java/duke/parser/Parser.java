package duke.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Parser is the class that parses raw user commands and executes the intended effect
 */
public class Parser {

    private final TaskList tasks;

    /**
     * Constructor for the Parser class
     * @param tasks   TaskList object which contains all the tasks in the program
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the raw command from the user, deciphers the intent and executes it. Adds/deletes/modifies tasks in
     * <code>task</code> and displays messages via <code>ui</code>. Displays error message in the event
     * that the command is not recognizable.
     *
     * @param fullCommand raw command provided as a String
     * @return true if the user enters "bye", a sign to terminate the program
     */
    public Response parse(String fullCommand) {
        try {
            StringBuilder text = new StringBuilder();
            ResponseStatus status = null;
            CommandType commandType = Parser.getCommandType(fullCommand);
            Task task;
            switch (commandType) {
            case DEADLINE:
            case TODO:
            case EVENT:
                task = Task.parseTask(commandType, fullCommand);
                tasks.add(task);
                text.append("Got it. I've added this task:\n  added: ");
                text.append(task);
                text.append("\nNow you have ");
                text.append(tasks.size());
                text.append(" tasks in the list.");
                status = ResponseStatus.OK;
                break;
            case LIST:
                if (tasks.size() == 0) {
                    text.append("There are no tasks recorded!");
                    break;
                }
                text.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    text.append(i + 1);
                    text.append(".");
                    text.append(task);
                    if (i != tasks.size() - 1) {
                        text.append("\n");
                    }
                }
                status = ResponseStatus.OK;
                break;
            case DELETE:
                try {
                    int deletionChoice = Integer.parseInt(fullCommand.split(" ")[1]);
                    if (deletionChoice > tasks.size()) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                    }
                    task = tasks.remove(deletionChoice - 1);
                    text.append("Noted. I've removed this task:\n  ");
                    text.append(task);
                    text.append("\nNow you have ");
                    text.append(tasks.size());
                    text.append(" tasks in the list.");
                    status = ResponseStatus.OK;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task to delete :-(");
                }
                break;
            case BYE:
                Platform.exit();
                System.exit(0);
                status = ResponseStatus.EXIT;
                break;
            case FIND:
                String query = fullCommand.split(" ", 2)[1];
                text.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    if (task.contains(query)) {
                        text.append(i + 1);
                        text.append(".");
                        text.append(task);
                        if (i != tasks.size() - 1) {
                            text.append("\n");
                        }
                    }
                }
                status = ResponseStatus.OK;
                break;
            case DONE:
                try {
                    int doneChoice = Integer.parseInt(fullCommand.split(" ")[1]);
                    if (doneChoice > tasks.size()) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                    }
                    Task taskDone = tasks.get(doneChoice - 1);
                    taskDone.markComplete();
                    text.append("Nice! I've marked this task as done:\n");
                    text.append(taskDone);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task to delete :-(");
                }
                break;
            case CLEAR:
                tasks.clear();
                text.append("The entire task list has been cleared!");
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return new Response(Ui.displayMessage(text.toString()), status);
        } catch (DukeException e) {
            return new Response(Ui.displayMessage(e.getMessage()), ResponseStatus.ERROR);
        }
    }

    public static CommandType getCommandType(String userCommand) {
        Map<CommandType, Pattern> patterns = new HashMap<>();
        patterns.put(CommandType.DONE, Pattern.compile("^d(one)?(?=\\s)+"));
        patterns.put(CommandType.TODO, Pattern.compile("^t(odo)?(?=\\s)+"));
        patterns.put(CommandType.EVENT, Pattern.compile("^e(vent)?(?=\\s)+"));
        patterns.put(CommandType.DEADLINE, Pattern.compile("^(deadline|dl)(?=\\s)+"));
        patterns.put(CommandType.LIST, Pattern.compile("^l(ist)?$"));
        patterns.put(CommandType.DELETE, Pattern.compile("^del(ete)?(?=\\s)+"));
        patterns.put(CommandType.BYE, Pattern.compile("^bye"));
        patterns.put(CommandType.FIND, Pattern.compile("^f(ind)?(?=\\s)+"));
        patterns.put(CommandType.CLEAR, Pattern.compile("^clear$"));
        for (CommandType type : patterns.keySet()) {
            if (patterns.get(type).matcher(userCommand.toLowerCase()).find()) {
                return type;
            }
        }
        return CommandType.UNRECOGNIZED;
    }
}
