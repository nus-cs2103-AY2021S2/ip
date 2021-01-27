package duke;

import duke.command.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    /**
     * A class containing static methods to parse input String for more information of what type of event it is.
     * Initialise a Parser that accepts in a String input Command, which it can Parse and determine the appropriate CommandType
     * to issue to the caller.
     *
     */

    private String inputCommand;

    //list of keyword Constants.
    private static String ADD_DEADLINE_COMMAND  = "deadline";
    private static String ADD_EVENT_COMMAND  = "event";
    private static String ADD_TODO_COMMAND = "todo";
    private static String DELETE_TASK_COMMAND = "delete";
    private static String LIST_COMMAND = "list";
    private static String MARK_DONE_COMMAND = "done";
    private static String EXIT_COMMAND = "bye";

    private static Pattern GET_KEYWORD = Pattern.compile("(\\S+).*");


    Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public Command parseCommand() throws DukeException{
        String command = getKeyWord(inputCommand).toLowerCase();
        switch(command) {
        case ADD_DEADLINE_COMMAND:
            Task t = parseAddDeadline(inputCommand);
            return new AddCommand(t);
        case ADD_EVENT_COMMAND:
            Task t = parseAddEvent(inputCommand);
            return new AddCommand(t);
        case ADD_TODO_COMMAND :
            Task t = parseAddToDo(inputCommand);
            return new AddCommand(t);
        case DELETE_TASK_COMMAND:
            int index = parseDelete(inputCommand);
            return new DeleteCommand(index);
        case MARK_DONE_COMMAND:
            int index = parseMarkDone(inputCommand);
            return new MarkTaskCommand(index);
        case LIST_COMMAND:
            parseListCommand();
            return new ListCommand();
        case EXIT_COMMAND:
            parseExitCommand();
            return new ExitCommand();
        default:
            throw new DukeException("");
        }
    }
    public void parseExitCommand(String inputCommand) throws DukeException {
        String regex = EXIT_COMMAND +"\\s*";
        if (!inputCommand.matches(regex)) {
            throw new DukeException("");
        }
    }

    public void parseListCommand(String inputCommand) throws DukeException {
        String regex = LIST_COMMAND +"\\s*";
        if (!inputCommand.matches(regex)) {
            throw new DukeException("");
        }
    }



    /**
     *
     * parse a string input MARK_DONE command to obtain the index of the task to mark as done.
     * @param input string.
     * @return  the index of the task to mark done.
     * @throws DukeException for the case when done is empty or when the integer cannot be parsed.
     */

    public int parseMarkDone(String input) throws DukeException {
        //for the case when "done" in the input string is followed by variable number of space.
        if (input.toLowerCase().matches("^done\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        String regex = "^done\\s+([0-9]+)$"; // "done" followed by at least one space and at least one number.
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            // Unable to parse the string following "done "
            throw new DukeException("The input for done must be integer.");
        }
        int indexToMarkDone = Integer.parseInt(m.group(1));
        return indexToMarkDone;
    }

    /**
     * Reads in a REMOVE_TASK command. Parses the string to obtain the index of the task to be deleted.
     * Prints out a message to tell the user the task is deleted
     * @param input string
     * @return index of the task to be deleted.
     * @throws DukeException when the delete is of the incorrect format or is empty.
     */

    public int parseDelete(String input) throws DukeException{
        //for the case when "delete" is followed by variable number of space.
        if (input.toLowerCase().matches("^delete\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        String regex = "^delete\\s+([0-9]+)$"; //delete followed by at least one space and one number.
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            throw new DukeException("The input for delete must be integer.");
        }
        int indexToDelete = Integer.parseInt(m.group(1));
        return indexToDelete;
    }

    /**
     * parses an input of the Command type ADD_TODO and returns the corresponding ToDo Task.
     * @param input string
     * @return ToDo Task.
     * @throws DukeException command Todo is empty or of the incorrect format.
     */

    public Task parseAddToDo (String input) throws DukeException{
        if (input.toLowerCase().matches("^todo\\s*$")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String regex = "^todo\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            throw new DukeException("The todo is of incorrect format.");
        }
        String description = m.group(1);
        return new ToDo(description);
    }

    /**
     * parses a string input of the ADD_DEADLINE command and returns the corresponidng deadline class.
     * @param input string
     * @return Deadline to be added.
     * @throws DukeException when the deadline is empty.
     */

    public Task parseAddDeadline(String input) throws DukeException {
        if (input.toLowerCase().matches("^deadline\\s*$")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String regex = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            throw new DukeException("The deadline is of incorrect format.");
        }
        String description = m.group(1);
        String by = m.group(2);
        return new Deadline(description,by);
    }

    /**
     * parses an input of the type ADD_EVENT, returns the correspoinding event class.
     * @param input string
     * @return Event Task
     * @throws DukeException when the event command is empty or of incorrect format.
     */

    public Task parseAddEvent(String input) throws DukeException{
        if (input.toLowerCase().matches("^event\\s*$")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String regex = "^event\\s+(.+)\\s+/at\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The event is of incorrect format.");
        }
        String description = m.group(1);
        String at = m.group(2);
        return new Event(description,at);
    }

    public String getKeyWord(String inputCommand) {
        Matcher m = GET_KEYWORD.matcher(this.inputCommand);
        return m.group(1);
    }




    /**
     * Static method for parsing the string from duke.txt file.
     * @param input
     * @return
     */
    public static Task parseTaskFromStoredFormat(String input){
        String[] fields = input.split(" \\| ");
        String commandCode = fields[0];
        Task parsedTask;
        switch(commandCode) {
        case("T"):
            parsedTask = new ToDo(fields[2]);
            break;
        case("D"):
            parsedTask = new Deadline(fields[2],fields[3]);
            break;
        case("E"):
            parsedTask = new Event(fields[2],fields[3]);
            break;
            //default case throw a ParseError to be defined later...
        default:
            parsedTask = null;
        }
        boolean isDone = (Integer.parseInt(fields[1]) == 1);
        if (isDone && (parsedTask != null)) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }


    // Methods for extracting dates and formatting dates.


    /**
     * finds date within the string
     * @param input
     * @return The sustring containing the date only
     */


    public static String extractDate(String input){
        String regex = "\\d+[-]\\d+[-]\\d+";
        Pattern datePattern  = Pattern.compile(regex);
        Matcher m = datePattern.matcher(input);
        if(m.find()){
            return m.group(0);
        } else {
            return "";
        }
    }

    public static LocalDate parseDate (String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(input,dateTimeFormatter);
    }

    public static class ParseException extends Exception {
        ParseException(String message){
            super(message);
        }
    }


}
