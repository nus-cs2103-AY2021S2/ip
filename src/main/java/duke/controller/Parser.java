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
    private final String command;
    private final String argument;
    private final String optionalArgument;

    /**
     * Constructor method that initiates with empty/ null values
     */
    public Parser() {
        this.command = "";
        this.optionalArgument = null;
        this.argument = "";
    }

    /**
     * @param in - a string that will be parsed and stored as
     *           <code>command</code>, <code>argument</code> and <code>optionalArgument</code> accordingly
     * @throws DukeException.UnknownCommandException if unknown command entered
     * @throws DukeException.NoDescriptionException  if required no. of arg is not met
     */
    public Parser(String in) {
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
        this.command = tempCommand;
        this.argument = tempArg;
        this.optionalArgument = tempDate;
    }

    /**
     * Getter method
     *
     * @return the private variable command
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Getter method
     *
     * @return the private variable argument
     */
    public String getDate() {
        return this.argument;
    }

    /**
     * @param inputList - take in the list and do corresponding action according to the command
     * @return a string to be printed to the console by UI
     */
    public String print(TaskList inputList) {
        Command switchVal = Command.valueOf(this.command.toUpperCase());
        switch (switchVal) {
        case BYE:
            return "Bye. Hope to see you again soon!";
        case LIST:
            String initStr = "Here are the tasks in your list:";
            for (int i = 0; i < inputList.getListItems().size(); i++) {
                initStr += "\n" + ((i + 1) + "." + inputList.getListItems().get(i));
            }
            return initStr;
        case DONE:
            inputList.markItemAsDone(Integer.parseInt(this.argument));
            return "Nice! I've marked this task as done: \n"
                    + inputList.getListItems().get(Integer.parseInt(this.argument) - 1);
        case EVENT:
            Event newEvent = new Event(this.argument, this.optionalArgument);
            inputList.addCommandMutable(newEvent);
            return printPredefinedMessage(newEvent.toString(), inputList);
        case DEADLINE:
            Deadline newDeadline = new Deadline(this.argument, this.optionalArgument);
            inputList.addCommandMutable(newDeadline);
            return printPredefinedMessage(newDeadline.toString(), inputList);
        case TODO:
            Todo newTodo = new Todo(this.argument);
            inputList.addCommandMutable(newTodo);
            return printPredefinedMessage(newTodo.toString(), inputList);
        case ERROR:
            return this.argument;
        case DELETE:
            int index = Integer.parseInt(this.argument);
            ListItem tempItem = inputList.getListItems().get(index - 1);
            inputList.deleteCommandMutable(index);
            return "Noted. I've removed this task: " + tempItem
                    + "\nNow you have " + inputList.getListItems().size() + " tasks in the list";
        case FIND:
            String matchedStr = "Here are the tasks in your list that fulfills your requirement:";
            TaskList tempList = inputList.findMatchingItems(this.argument);
            for (int i = 0; i < tempList.getListItems().size(); i++) {
                matchedStr += "\n" + ((i + 1) + "." + tempList.getListItems().get(i));
            }
            return matchedStr;
        case TAG:
            inputList.updateItemTag(Integer.parseInt(this.argument), this.optionalArgument);
            return "Nice! I've marked task " + this.argument + " with the tag: \n#"
                    + this.optionalArgument;
        default:
            return "";
        //every case must return some form of string, therefore break is not required
        }
    }

    /**
     * creates a standardised string that is common for all the tasks type to be printed
     *
     * @param typeOfTask
     * @param inputList  - to get the size of the list
     * @return a string to be printed by UI
     */
    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have "
                + inputList.getListItems().size() + " tasks in the list";
    }
}
