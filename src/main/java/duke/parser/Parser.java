package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.HelpCommand;
import duke.command.ListByPriorityCommand;
import duke.command.ListCommand;
import duke.command.SearchByTaskNameCommand;
import duke.command.SearchByTimeCommand;
import duke.exception.DukeException;



/**
 * Represents a parser object that will read the input from
 * the users and then parse the input to the program.
 */
public class Parser {
    /**
     * The function takes the user message and then parse it to return the command objects
     * for further procedures.
     *
     * @param userMessage The message the user inputs.
     * @throws DukeException if the user input message can't be parsed.
     * @return a specific subclass of the Command object. eg. ListCommand object.
     */
    public static Command parse(String userMessage) throws DukeException {
        if (userMessage.equals("list")) {
            return new ListCommand(userMessage);
        } else if (userMessage.equals("list -p")) {
            return new ListByPriorityCommand(userMessage);
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
        } else if (userMessage.startsWith("help")) {
            return new HelpCommand(userMessage);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

