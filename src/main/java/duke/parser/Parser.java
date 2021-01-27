package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    public static Command parse(String userMessage) throws DukeException {
        if (userMessage.equals("list")) {
            return new ListCommand(userMessage);
        }

        // Level-3 : When user want a task done:
        else if(userMessage.startsWith("done")){
            return new DoneCommand(userMessage);
        }

        // Level-4 : When user add tasks(T,D,E):
        // Todo
        else if (userMessage.startsWith("todo")) {
            return new AddToDoCommand(userMessage);
        }
            // Deadline
        else if (userMessage.startsWith("deadline")) {
            return new AddDeadlineCommand(userMessage);
        }
            // Event
        else if (userMessage.startsWith("event")) {
            return new AddEventCommand(userMessage);
        }

        else if (userMessage.startsWith("delete")) {
            return new DeleteCommand(userMessage);
        }

        else if (userMessage.startsWith("search time")) {
            return new SearchByTimeCommand(userMessage);
        }
        
        else if (userMessage.startsWith("find")) {
            return new SearchByTaskNameCommand(userMessage);
        }

        else if(userMessage.equals("bye")) {
            return new ByeCommand(userMessage);
        }

        else throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

    }
}

