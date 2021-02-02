package duke.parser;

import duke.command.*;

import duke.exception.DukeException;



/**
 * Represents a parser object that will read the input from
 * the users and then parse the input to the program.
 */
public class Parser {
    /**
     * The function will take the user message and then parse it to return the command objects
     * for further procedures.
     *
     * @param userMessage The message the user inputs.
     * @throws DukeException if the user input message can't be parsed.
     * @return a specific subclass of the Command object. eg. ListCommand object.
     */
    public static Command parse(String userMessage) throws DukeException {
        if (userMessage.equals("list")) {
            return new ListCommand(userMessage);
        } else if (userMessage.startsWith("done")) {
            return new DoneCommand(userMessage);
        } else if (userMessage.startsWith("todo")) {
            return new AddToDoCommand(userMessage);
        } else if (userMessage.startsWith("deadline")) {
            return new AddDeadlineCommand(userMessage);
        } else if (userMessage.startsWith("event")) {
            return new AddEventCommand(userMessage);
        } else if (userMessage.startsWith("delete")) {
            return new DeleteCommand(userMessage);
        } else if (userMessage.startsWith("search time")) {
            return new SearchByTimeCommand(userMessage);
        } else if (userMessage.equals("bye")) {
            return new ByeCommand(userMessage);
        } else if (userMessage.startsWith("find")) {
            return new SearchByTaskNameCommand(userMessage);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

