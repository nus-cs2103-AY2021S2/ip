package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;
import command.CommandDetails;

import duke.DukeException;
import duke.Tag;

import command.DukeCommand;

/**
 * Parser is involved with parsing user commands in Duke.
 */
public class Parser {

    /**
     * Parses user input to create a DukeCommand
     * @param input User input to be parsed.
     * @return a DukeCommand that contains a Command and a String containing more details
     * @throws Exception if user input is unrecognisable by Parser logic
     */
    public static DukeCommand parseCommand(String input) throws Exception {
        String[] params = input.strip().split(" ", 2);

        if (input.equals("bye")) {
            return new DukeCommand(Command.BYE, new CommandDetails(Command.BYE));

        } else if (params[0].equals("delete")) {
            if (params.length == 1) {
                throw new DukeException("delete is missing 1 argument");
            }
            if (Integer.parseInt(params[1]) <= 0) {
                throw new DukeException("Non-positive integers is now allowed as a argument to delete");
            }
            return new DukeCommand(Command.DELETE, new CommandDetails(Command.DELETE, params[1].stripLeading()));

        } else if (input.equals("list")) {
            return new DukeCommand(Command.LIST, new CommandDetails(Command.LIST));

        } else if (params[0].equals("done")) {
            if (params.length == 1) {
                throw new DukeException("done is missing 1 argument");
            }
            if (Integer.parseInt(params[1]) <= 0) {
                throw new DukeException("Non-positive integers is now allowed as a argument to done");
            }
            return new DukeCommand(Command.DONE, new CommandDetails(Command.DONE, params[1].stripLeading()));

        } else if (params[0].equals("todo")) {
            if (params.length == 1) {
                throw new DukeException("todo is missing 1 argument");
            }
            return new DukeCommand(Command.TODO, parseRemainder(Command.TODO, params[1].stripLeading()));

        } else if (params[0].equals("deadline")) {
            if (params.length == 1) {
                throw new DukeException("Missing arguments for deadline");
            }
            return new DukeCommand(Command.DEADLINE, parseRemainder(Command.DEADLINE, params[1].stripLeading()));

        } else if (params[0].equals("event")) {
            if (params.length == 1) {
                throw new DukeException("Missing arguments for event");
            }
            return new DukeCommand(Command.EVENT, parseRemainder(Command.EVENT, params[1].stripLeading()));

        } else if (params[0].equals("find")) {
            if (params.length == 1) {
                throw new DukeException("Missing keyword for find");
            }
            return new DukeCommand(Command.FIND, new CommandDetails(Command.FIND, params[1].stripLeading()));

        } else if (params[0].equals("tag")) {
            if (params.length == 1) {
                throw new DukeException("Missing arguments for tag");
            }

            String inputDetails = new String(params[1]);
            int startIndexOfTaskDesc = inputDetails.indexOf(" \"");
            int endIndexOfTaskDesc = inputDetails.indexOf("\" ");
            if (startIndexOfTaskDesc == -1 || endIndexOfTaskDesc == -1) {
                throw new DukeException("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                        + " tag is missing some arguments!");
            }
            String taskDescription = inputDetails.substring(startIndexOfTaskDesc + 2, endIndexOfTaskDesc);
            String tagMode = inputDetails.stripLeading().substring(0, startIndexOfTaskDesc);
            String tag = inputDetails.stripTrailing().substring(endIndexOfTaskDesc + 1).stripLeading();
            if (!tagMode.equals("add") && !tagMode.equals("delete")) {
                throw new DukeException("OOPS! tag is missing some arguments!");
            }

            return new DukeCommand(Command.TAG, new CommandDetails(Command.TAG, tagMode, taskDescription, tag));

        } else {
            return new DukeCommand(Command.INVALID, new CommandDetails(Command.INVALID));
        }
    }

    /**
     * Parses a DukeCommand further if necessary.
     * @param command a Command
     * @param details Additional details on the command
     * @return a CommandDetails parsed from the user's input
     * @throws Exception if user's input for Todo, Event and Deadline from the enum Command is not acceptable.
     */
    public static CommandDetails parseRemainder(Command command, String details) throws Exception {
        if (command == Command.TODO) {
            Pattern pt1 = Pattern.compile("\\w+");
            Pattern pt2 = Pattern.compile("\\D+");

            Matcher mt1 = pt1.matcher(details);
            Matcher mt2 = pt2.matcher(details);

            if (!mt1.find() || !mt2.find()) {
                throw new DukeException("OOPS!!! The description of a todo should have at least "
                        + "one non digit word character");
            }

            return new CommandDetails(command, details);

        } else if (command == Command.DEADLINE) {
            String[] deadlineParams = details.split(" /by ");

            if (deadlineParams.length == 1) {
                throw new DukeException("deadline not given for this Deadline!");
            }

            Pattern pt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher mt = pt.matcher(deadlineParams[1].strip());
            if (!mt.find()) {
                throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD' after description");
            }

            return new CommandDetails(command, deadlineParams[0], deadlineParams[1]);

        } else if (command == Command.EVENT) {
            String[] eventParams = details.split(" /at ");
            if (eventParams.length == 1) {
                throw new DukeException("no date and time given for this Event!");
            }

            String[] timeParams = eventParams[1].strip().split(" ");
            if (timeParams.length == 1) {
                throw new DukeException("time of Event was not specified!");
            }

            Pattern datePt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher dateMt = datePt.matcher(timeParams[0]); // timeParams[0] refers to the date
            if (!dateMt.find()) {
                throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD {time range}' after description");
            }

            Pattern timePt = Pattern.compile("\\d{1,2}-\\d{1,2}p?a?m");
            Matcher timeMt = timePt.matcher(timeParams[1]); // timeParams[1] refers to the time
            if (!timeMt.find()) {
                throw new DukeException("OOPS!!! Please enter a valid time range in this format \"{start}-{end}\""
                        + " and include am/pm after");
            }

            return new CommandDetails(command, eventParams[0], timeParams[0], timeParams[1]);
            
        } else {
            throw new AssertionError("Parser.parseRemainder() was given a Command other than Todo, "
                    + "Deadline and Event");
        }
    }
}
