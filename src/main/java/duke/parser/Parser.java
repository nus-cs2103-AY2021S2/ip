package duke.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnrecognizableCommand;
import duke.tasks.TaskList;

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
     * <code>task</code> and displays messages via ui. Displays error message in the event
     * that the command is not recognizable.
     *
     * @param fullCommand raw command provided as a String
     * @return true if the user enters "bye", a sign to terminate the program
     */
    public Command parse(String fullCommand) {
        CommandType commandType = Parser.getCommandType(fullCommand);
        switch (commandType) {
        case DEADLINE:
        case TODO:
        case EVENT:
            return new TodoCommand(commandType, fullCommand);
        case LIST:
            return new ListCommand(commandType, fullCommand);
        case DELETE:
            return new DeleteCommand(commandType, fullCommand);
        case BYE:
            return new ExitCommand(commandType, fullCommand);
        case FIND:
            return new FindCommand(commandType, fullCommand);
        case DONE:
            return new DoneCommand(commandType, fullCommand);
        case CLEAR:
            return new ClearCommand(commandType, fullCommand);
        default:
            return new UnrecognizableCommand();
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
