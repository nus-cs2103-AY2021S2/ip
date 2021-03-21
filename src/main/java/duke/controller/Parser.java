package duke.controller;

import duke.Duke;
import duke.Helper;
import duke.model.Command;
import duke.model.exception.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

import java.util.List;

/**
 * Represents a parser that takes in the entered <code>command</code> by the user and filtered by the enum,
 * then return the parsed <code>command</code>, <code>argument</code> and <code>date</code>
 * <code>print</code> the output and add the parsed item to the list if needed
 *
 * <code>command</code> stores the command that extracts from the string passing into the constructor
 * <code>argument</code> stores the first argument for the command e.g. index number for DONE
 * <code>optionalArgument</code> stores the optional argument for the command such as the dates for DEADLINE and EVENT
 */
public class Parser {
    private final Command command;
    private final String argument;
    private final String optionalArgument;

    /**
     * Constructor method that initiates with empty/ null values
     */
    public Parser() {
        this.command = Command.ERROR;
        this.optionalArgument = "";
        this.argument = "";
    }

    /**
     * @param in - a string that will be parsed and stored as
     *           <code>command</code>, <code>argument</code> and <code>optionalArgument</code> accordingly
     * @throws DukeException.UnknownCommandException if unknown command entered
     * @throws DukeException.NoDescriptionException  if required no. of arg is not met
     */
    private Parser(Command command, String argument, String optionalArgument) {
        this.command = command;
        this.argument = argument;
        this.optionalArgument = optionalArgument;
    }

    public static Parser createParser(String in, TaskList tasks) {
        try {
            return parseWithFormatCheck(in, tasks);
        } catch (DukeException ex) {
            return new Parser(Command.ERROR, ex.getMessage(), "");
        }
    }

    private static Parser parseWithFormatCheck(String in, TaskList tasks) throws DukeException {
        String tempDate = null;
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = "";
        switch (result[0].toLowerCase()) {
            case "find":
            case "todo": // both only need a name in a form of string therefore grouped
                tempCommand = result[0];
                if (result.length <= 1) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    tempArg = result[1];
                }
                break;
            case "done":
            case "delete": // both requires an index of the item
                String indexOfItemAsString = in.substring(in.indexOf(" ") + 1);
                tempArg = indexOfItemAsString;
                if (!Helper.isInteger(indexOfItemAsString)) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else if (ListController.checkValidIndexForListOperation(Command.valueOf(result[0]), tempArg, tasks)) {
                    throw new DukeException.IndexOutOfListSizeException();
                } else {
                    tempCommand = result[0];
                }
                break;
            case "deadline":
            case "event": // both requires an extra string (date)
                checkDeadlineAndEventFormat(in, result[0]);
                int max = Math.max(in.indexOf("/by "), in.indexOf("/at "));
                String firstParam = in.substring(max);
                tempCommand = result[0];
                tempDate = firstParam.substring(4);
                tempArg = in.substring(in.indexOf(" "), max - 1);
                break;
            case "tag":
                tempCommand = result[0];
                if (!Helper.isInteger(result[1])) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else if (ListController.checkValidIndexForListOperation(Command.valueOf(result[0]), result[1], tasks)) {
                    throw new DukeException.IndexOutOfListSizeException();
                } else {
                    tempArg = result[1];
                }
                tempDate = result[2];
                break;
            default:
                // try parsing the command first by finding the equivalent in Enum PredefinedCommand,
                // else throw exception
                try {
                    tempCommand = String.valueOf(Command.valueOf(result[0].toUpperCase())).toLowerCase();
                } catch (IllegalArgumentException ex) {
                    throw new DukeException.UnknownCommandException();
                }
                break;
        }
        return new Parser(Command.valueOf(tempCommand.toUpperCase()), tempArg, tempDate);
    }

    private static boolean checkDeadlineAndEventFormat(String firstParam, String result) throws DukeException.NoDescriptionException {
        int dateIndex = Math.max(firstParam.indexOf("/by "), firstParam.indexOf("/at "));
        if (dateIndex == -1) {
            throw new DukeException.NoDescriptionException(result);
        } else {
            String prefix = firstParam.substring(dateIndex, dateIndex + 4);
            if ((prefix.equals("/at ") && result.equals("deadline")) || (prefix.equals("/by ") && result.equals("event"))) {
                throw new DukeException.NoDescriptionException(result);
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

    public String getArgument() {
        return argument;
    }

    public String getOptionalArgument() {
        return optionalArgument;
    }

    /**
     * Getter method
     *
     * @return the private variable argument
     */
    public String getDate() {
        return this.argument;
    }
}
