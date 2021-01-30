package duke;

import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser is involved with parsing user commands in Duke.
 */
public class Parser {

    /**
     * Parses user input to create a DukeCommand
     * @param input User input to be parsed.
     * @return a DukeCommand that contains a Command and a String
     * containing more details
     */
    public static DukeCommand parseCommand(String input) {
        try {
            String[] params = input.split(" ", 2);

            if (input.equals("bye")) {
                return new DukeCommand(Command.BYE, "");

            } else if (params[0].equals("delete")) {
                return new DukeCommand(Command.DELETE, params[1]);

            } else if (input.equals("list")) {
                return new DukeCommand(Command.LIST, "");

            } else if (params[0].equals("done")) {
                return new DukeCommand(Command.DONE, params[1]);

            } else if (params[0].equals("todo")) {
                return new DukeCommand(Command.TODO, params[1]);

            } else if (params[0].equals("deadline")) {
                return new DukeCommand(Command.DEADLINE, params[1]);

            } else if (params[0].equals("event")) {
                return new DukeCommand(Command.EVENT, params[1]);

            } else if (params[0].equals("find")) {
                return new DukeCommand(Command.FIND, params[1]);
            } else {
                return new DukeCommand(Command.INVALID, "");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Parses a DukeCommand further if necessary.
     *
     * @param command a Command
     * @param details Additional details on the command
     * @return the Task the user intends to create
     */
    public static Task parseRemainder(Command command, String details) {
        try {
            if (command == Command.TODO) {
                if (details.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                }

                Pattern pt1 = Pattern.compile("\\w+");
                Pattern pt2 = Pattern.compile("\\D+");

                Matcher mt1 = pt1.matcher(details);
                Matcher mt2 = pt2.matcher(details);

                if (!mt1.find() || !mt2.find()) {
                    throw new DukeException("OOPS!!! The description of a todo should have at least one non digit word character");
                }

                return new Todo(details);

            } else if (command == Command.DEADLINE) {
                if (details.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                }

                String[] deadlineParams = details.split(" /by ");

                if (deadlineParams.length == 1) {
                    throw new DukeException("deadline not given for this Deadline!");
                }

                Pattern pt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                Matcher mt = pt.matcher(deadlineParams[1]);

                if (!mt.find()) {
                    throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD' after description");
                }

                return new Deadline(deadlineParams[0], LocalDate.parse(deadlineParams[1]));

            } else {

                if (details.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
                }

                String[] eventParams = details.split(" /at ");

                if (eventParams.length == 1) {
                    throw new DukeException("no date and time given for this Event!");
                }

                String[] timeParams = eventParams[1].split(" ");

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
                    throw new DukeException("OOPS!!! Please enter a valid time range in this format \"{start}-{end}\"" +
                            " and include am/pm after");
                }

                return new Event(eventParams[0], LocalDate.parse(timeParams[0]), timeParams[1]);
            }

        } catch(DukeException err) {
            Ui.showDukeException(err);
        } catch(Exception err) {
            err.printStackTrace();
        }

        return null;
    }

}
