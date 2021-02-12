package duke.command;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Statement class deals with the user's input and make sense of it.
 * Deals with most of the input errors. Has a table that store number of arguments needed
 * for each command.
 */
public class Statement {
    private final String statement;
    private final Hashtable<String, Integer> argsTable;

    /**
     * Returns a Statement object that represent the user's input.
     *
     * @param statement The user's current input.
     */
    public Statement(String statement) {
        this.statement = statement;
        argsTable = new Hashtable<>();
        initializeHashTable();
    }

    private void initializeHashTable() {
        argsTable.put("list", 0);
        argsTable.put("bye", 0);
        argsTable.put("done", 1);
        argsTable.put("todo", 1);
        argsTable.put("find", 1);
        argsTable.put("delete", 1);
        argsTable.put("priority", 1);
        argsTable.put("deadline", 2);
        argsTable.put("event", 2);
    }

    //return a list of String with the first element as the command
    //the second element is description and third element is time (if applicable)
    private List<String> parseStatement() throws DukeException {
        Scanner sc = new Scanner(statement);
        String command = sc.next();

        List<String> result = new ArrayList<>();
        result.add(command);

        if (!argsTable.containsKey(command)) {
            //unknown command
            String unknownCommandError = "Sorry, but I don't know what " + command + " means. :(";
            throw new DukeException(unknownCommandError);
        }

        int numOfArgs = argsTable.get(command);
        //there are no more arguments
        if (numOfArgs == 0) {
            return result;
        }

        if (!sc.hasNext()) {
            throw getError(command);
        }

        String rest = sc.nextLine();
        String[] args = rest.split("[/]");

        //missing arguments
        if (args.length != numOfArgs) {
            String missingDescAndDateError = "OOPS! " + command + " requires a description and a time.";
            throw new DukeException(missingDescAndDateError);
        }

        for (int i = 0; i < numOfArgs; i++) {
            result.add(args[i]);
        }

        return result;
    }

    private DukeException getError(String command) {
        String missingIndexError = "OOPS! " + command + " requires the index of the task.";
        String missingIndexAndPriorError = "OOPS! " + command
                + " requires the index and the priority of the task.";
        String missingDescriptionError = "OOPS! " + command + " requires a description.";
        String missingKeywordError = "Please provide a keyword.";
        String missingDescAndDateError = "OOPS! " + command + " requires a description and a time.";

        switch(command) {
        case "done": case "delete":
            return new DukeException(missingIndexError);
        case "priority":
            return new DukeException(missingIndexAndPriorError);
        case "todo":
            return new DukeException(missingDescriptionError);
        case "find":
            return new DukeException(missingKeywordError);
        case "deadline": case "event":
            return new DukeException(missingDescAndDateError);
        default:
            return new DukeException("Something's wrong...");
        }
    }

    /**
     * Returns a Command object after making sense of the user's input.
     *
     * @return A Command object representing a command from the user.
     * @throws DukeException If the format of date provided for event/deadline
     * is incorrect or there is no '/' to separate description and date of an
     * event/deadline.
     */
    public Command parse() throws DukeException {
        try {
            List<String> parsedArgs = parseStatement();
            String command = parsedArgs.get(0);
            String description = null;
            String preposition = null;
            LocalDate date = null;


            if (parsedArgs.size() >= 2) {
                description = parsedArgs.get(1).trim();
            }

            if (parsedArgs.size() == 3) {
                String second = parsedArgs.get(2).trim();
                String[] prepositionAndDate = second.split("[\\s]");

                if (prepositionAndDate.length != 2) {
                    String missingDateError = "Please provide a preposition and a date after '/'.";
                    throw new DukeException(missingDateError);
                }

                preposition = prepositionAndDate[0];
                date = LocalDate.parse(prepositionAndDate[1]);
            }
            return getTask(command, description, preposition, date);

        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in the format yyyy-mm-dd.");
        }

    }

    private Command getTask(String command, String description, String preposition, LocalDate date) {
        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            return new DoneCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "find":
            return new FindCommand(description);
        case "priority":
            return new PriorityCommand(description);
        case "todo": case "deadline": case "event":
            return new AddCommand(command, description, preposition, date);
        default:
            return null;
        }
    }
}
