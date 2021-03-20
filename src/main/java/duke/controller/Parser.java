package duke.controller;

import duke.Helper;
import duke.model.Command;
import duke.model.exception.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

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
        this.optionalArgument = null;
        this.argument = "";
    }

    /**
     * @param in - a string that will be parsed and stored as
     *           <code>command</code>, <code>argument</code> and <code>optionalArgument</code> accordingly
     * @throws DukeException.UnknownCommandException if unknown command entered
     * @throws DukeException.NoDescriptionException  if required no. of arg is not met
     */
    private Parser(String in) {
        String tempDate = null;
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = "";
        // has temporary variables tempArg, tempDate, tempCommand as they are "final"
        try {
            // check the first part of the input string and decide what to do next using switch case
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
                    } else {
                        tempCommand = result[0];
                    }
                    break;
                case "deadline":
                case "event": // both requires an extra string (date)
                    String firstParam = in.substring(in.indexOf("/") + 1);
                    if (firstParam.equals("deadline") || firstParam.equals("event")) {
                        throw new DukeException.NoDescriptionException(result[0]);
                    } else {
                        // check if the date starts by "by" or "at" to classify them
                        int dateIndex = Math.max(firstParam.indexOf("by "), firstParam.indexOf("at "));
                        if (dateIndex == -1) {
                            throw new DukeException.NoDescriptionException(result[0]);
                        } else {
                            tempCommand = result[0];
                            tempDate = firstParam.substring(dateIndex + 3);
                            firstParam = in.substring(in.indexOf(" ") + 1);
                            tempArg = firstParam.substring(0, firstParam.indexOf("/") - 1);
                        }
                    }
                    break;
                case "tag":
                    tempCommand = result[0];
                    tempArg = result[1];
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
        } catch (DukeException ex) {
            tempCommand = "error";
            tempArg = ex.getMessage();
        }
        this.command = Command.valueOf(tempCommand.toUpperCase());;
        this.argument = tempArg;
        this.optionalArgument = tempDate;
    }

    public static Parser createParser(String in) {
        return new Parser(in);
    }

    /**
     * Getter method
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
