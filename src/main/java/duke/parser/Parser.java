package duke.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
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
    public String parse(String fullCommand) {
        try {
            StringBuilder response = new StringBuilder();
            CommandType commandType = Parser.getCommandType(fullCommand);
            Task task;
            switch (commandType) {
            case DEADLINE:
            case TODO:
            case EVENT:
                task = Task.parseTask(fullCommand);
                tasks.add(task);
                response.append("Got it. I've added this task:\n  added: ");
                response.append(task);
                response.append("\nNow you have ");
                response.append(tasks.size());
                response.append(" tasks in the list.");
                break;
            case LIST:
                response.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    response.append(i + 1);
                    response.append(".");
                    response.append(task);
                    if (i != tasks.size() - 1) {
                        response.append("\n");
                    }
                }
                break;
            case DELETE:
                int userChoice = Integer.parseInt(fullCommand.split(" ")[1]);
                if (userChoice > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                }
                task = tasks.remove(userChoice - 1);
                response.append("Noted. I've removed this task:\n  ");
                response.append(task);
                response.append("\nNow you have ");
                response.append(tasks.size());
                response.append(" tasks in the list.");
                break;
            case BYE:
                Platform.exit();
                System.exit(0);
                break;
            case FIND:
                String query = fullCommand.split(" ", 2)[1];
                response.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    if (task.contains(query)) {
                        response.append(i + 1);
                        response.append(".");
                        response.append(task);
                        if (i != tasks.size() - 1) {
                            response.append("\n");
                        }
                    }
                }
                break;
            case UNRECOGNIZED:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return Ui.displayMessage(response.toString());
        } catch (DukeException e) {
            return Ui.displayMessage(e.getMessage());
        }
    }

    public static CommandType getCommandType(String userCommand) {
        Map<CommandType, Pattern> patterns = new HashMap<>();
        patterns.put(CommandType.DONE, Pattern.compile("^d(one)(?=\\s)?"));
        patterns.put(CommandType.TODO, Pattern.compile("^t(odo)(?=\\s)?"));
        patterns.put(CommandType.EVENT, Pattern.compile("^e(vent)(?=\\s)?"));
        patterns.put(CommandType.DEADLINE, Pattern.compile("^d(l|eadline)(?=\\s)?"));
        patterns.put(CommandType.LIST, Pattern.compile("^l(ist)(?=\\s)?"));
        patterns.put(CommandType.DELETE, Pattern.compile("^del(ete)(?=\\s)?"));
        for (CommandType type : patterns.keySet()) {
            if (patterns.get(type).matcher(userCommand).find()) {
                return type;
            }
        }
        return CommandType.UNRECOGNIZED;
    }
}
