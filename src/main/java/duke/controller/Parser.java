package duke.controller;

import duke.Helper;
import duke.model.Command;
import duke.model.exception.DukeException;
import duke.model.task.TaskList;

/**
 * Represents a parser that takes in input by the user which is filtered by the enum <code>Command</code>,
 * then store the parsed <code>command</code>, <code>argument</code> and <code>optionalArgument</code>
 * for ListController and UIController to interact
 *
 * <code>command</code> stores the command that extracts from the string passing into the constructor
 * <code>argument</code> stores the first argument for the command e.g. index number for DONE
 * <code>optionalArgument</code> stores the optional argument for the command such as the dates for DEADLINE and EVENT
 */
public class Parser {
    private final Command command;
    private final String argument;
    private final String optionalArgument; // mainly used for Command.TAG, DEADLINE or EVENT

    /**
     * Public constructor method that initiates with empty/ null values, used for starting Duke when App launches
     */
    public Parser() {
        this.command = Command.ERROR;
        this.optionalArgument = "";
        this.argument = "";
    }

    /**
     * Private constructor that creates a <code>Parser</code> with the correct <code>command</code>,
     * <code>argument</code> and <code>optionalArgument</code>
     * once the format checks and parsing are completed with <code>parseWithFormatCheck</code>
     * so that it would not be called by any other class explicitly specifying any properties with private
     */
    private Parser(Command command, String argument, String optionalArgument) {
        this.command = command;
        this.argument = argument;
        this.optionalArgument = optionalArgument;
    }

    /**
     * public method that allows other controller to create new Parser
     * to parse content with <code>parseWithFormatCHeck</code>, if any exception is caught,
     * error details will be used for creating the Parser instead of parsed content of user's provided input
     *
     * @param in    String to be parsed
     * @param tasks current status of TaskList
     * @return a new Parser
     */
    public static Parser createParser(String in, TaskList tasks) {
        try {
            return parseWithFormatCheck(in, tasks);
        } catch (DukeException ex) {
            return new Parser(Command.ERROR, ex.getMessage(), null);
        }
    }

    /**
     * parse the string with different format check and throw exception accordingly
     * if format is valid, set the value accordingly and call the private constructor of<code>Parser</code>
     *
     * @param in    String to be parsed
     * @param tasks current status of TaskList
     * @return a new Parser created with private constructor
     * @throws DukeException when there are invalid index, invalid or wrong argumennts or unknown command
     */
    private static Parser parseWithFormatCheck(String in, TaskList tasks) throws DukeException {
        String tempOptArg = null;
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = null;
        switch (result[0].toLowerCase()) {
        case "find":
        case "todo": // both only need a name in a form of string therefore grouped
            tempCommand = result[0];
            if (result.length <= 1) { // if `todo` and `find` doesnt have any text follow
                throw new DukeException.NoArgumentOrWrongFormatException(result[0]);
            } else {
                tempArg = in.substring(5); // `todo ` and `find ` takes 5 character spaces
            }
            break;
        case "done":
        case "delete": // both requires an index of the item
            String indexOfItemAsString = in.substring(in.indexOf(" ") + 1);
            tempArg = indexOfItemAsString;
            // first check if the argument is an integer to be used as index, then check if it falls within valid range
            if (!Helper.isInteger(indexOfItemAsString)) {
                throw new DukeException.NoArgumentOrWrongFormatException(result[0]);
            } else if (!ListController.checkValidIndexForListOperation
                    (Command.valueOf(result[0].toUpperCase()), tempArg, tasks)) {
                throw new DukeException.IndexOutOfListSizeException();
            } else {
                tempCommand = result[0];
            }
            break;
        case "deadline":
        case "event": // both requires an extra string (date)
            checkDeadlineAndEventFormat(in, result[0]); // checks if the input fulfills the specific format check
            int max = Math.max(in.indexOf("/by "), in.indexOf("/at ")); // find the index of /by or /at to extract text
            String firstParam = in.substring(max);
            tempCommand = result[0];
            tempOptArg = firstParam.substring(4);
            tempArg = in.substring(in.indexOf(" ") + 1, max - 1);
            break;
        case "tag":
            tempCommand = result[0];
            // first check if the argument is an integer to be used as index and has enough arguments,
            // then check if it falls within valid range
            // finally check if the tag includes a # also
            if (!Helper.isInteger(result[1]) || result.length != 3) {
                throw new DukeException.NoArgumentOrWrongFormatException(result[0]);
            } else if (!ListController.checkValidIndexForListOperation
                    (Command.valueOf(result[0].toUpperCase()), result[1], tasks)) {
                throw new DukeException.IndexOutOfListSizeException();
            } else if (result[2].contains("#")) {
                throw new DukeException.HashTagInTagArgumentException();
            } else {
                tempArg = result[1];
            }
            tempOptArg = result[2];
            break;
        default:
            // try parsing the command first by finding the equivalent in Enum PredefinedCommand else throw exception
            try {
                tempCommand = String.valueOf(Command.valueOf(result[0].toUpperCase())).toLowerCase();
            } catch (IllegalArgumentException ex) {
                throw new DukeException.UnknownCommandException();
            }
            break;
        }
        return new Parser(Command.valueOf(tempCommand.toUpperCase()), tempArg, tempOptArg);
    }

    /**
     * Used for checking deadline and event's format: <code>/by </code> and <code>/at </code>
     *
     * @param input String used to check if /by or /at exists and match <code>type</code>
     * @param type  <code>deadline</code> or <code>event</code>
     * @return a boolean showing it fulfills the format or not
     * @throws DukeException.NoArgumentOrWrongFormatException informs user invalid format
     */
    private static boolean checkDeadlineAndEventFormat(String input, String type)
            throws DukeException.NoArgumentOrWrongFormatException {
        int prefixIndex = Math.max(input.indexOf("/by "), input.indexOf("/at "));
        if (prefixIndex == -1) {
            throw new DukeException.NoArgumentOrWrongFormatException(type);
        } else {
            String prefix = input.substring(prefixIndex, prefixIndex + 4);
            if ((prefix.equals("/at ") && type.equals("deadline"))
                    || (prefix.equals("/by ") && type.equals("event"))) {
                throw new DukeException.NoArgumentOrWrongFormatException(type);
            }
        }
        return true;
    }

    /**
     * Getter method
     *
     * @return the private variable command
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Getter method
     *
     * @return the private variable argument
     */
    public String getArgument() {
        return argument;
    }

    /**
     * Getter method
     *
     * @return the private variable optionalArgument
     */
    public String getOptionalArgument() {
        return optionalArgument;
    }
}
