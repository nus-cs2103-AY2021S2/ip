package component;

import command.*;

import exception.EmptyDescriptionException;
import exception.UnknownCommandException;
import exception.WrongFormatException;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Parser {
    public static Command parse(String command) throws UnknownCommandException, EmptyDescriptionException, WrongFormatException {
        String[] parameters = command.split(" ");
        switch (parameters[0]) {
        case "bye": {
            return new ExitCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "done": {
            return new DoneCommand(Integer.parseInt(parameters[1]) - 1);
        }
        case "delete": {
            return new DeleteCommand(Integer.parseInt(parameters[1]) - 1);
        }
        case "todo": {
            if (command.trim().length() <= 4) {
                throw new EmptyDescriptionException("todo");
            }
            ToDo td = new ToDo(command.substring(5, command.length()));
            return new AddCommand(td);
        }
        case "deadline": {
            int slash = command.indexOf("/by");
            if (slash == -1) {
                throw new WrongFormatException();
            }
            else if (slash <= 10 || parameters.length <= 2) {
                throw new EmptyDescriptionException("deadline");
            }
            Deadline dl = new Deadline(command.substring(9, slash - 1), command.substring(slash + 4, command.length()));
            return new AddCommand(dl);
        }
        case "event": {
            int slash = command.indexOf("/at");
            if (slash == -1) {
                throw new WrongFormatException();
            }
            else if (slash <= 7 || parameters.length <= 2) {
                throw new EmptyDescriptionException("event");
            }
            Event e = new Event(command.substring(6, slash - 1), command.substring(slash + 4, command.length()));
            return new AddCommand(e);
        }
        default: {
            throw new UnknownCommandException();
        }
        }
    }
}
