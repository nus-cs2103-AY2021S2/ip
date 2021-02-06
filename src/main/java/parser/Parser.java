package parser;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import exceptions.DukeException;
import tasks.Deadlines;
import tasks.Events;
import tasks.Todo;

public class Parser {

    /**
     * Parses the input given and returns a command to be executed.
     *
     * @param line  TaskList object.
     * @return The respective Command based on the input.
     * @throws DukeException When the Index, Description or Details are empty based on the different Tasks.
     */
    public static Command parse(String line) throws DukeException {
        String[] doneLine = line.split(" ", 2);
        line = doneLine[0]; // get the first keyword
        int index = 0;

        if (line.equals("done") || line.equals("delete")) {
            try {
                index = Integer.parseInt(doneLine[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("EmptyIndex");
            }
        }

        switch (line) {
        case ("bye"):
            return new ByeCommand();
        case("help"):
            return new HelpCommand();
        case ("list"):
            return new ListCommand();
        case ("done"):
            return new DoneCommand(index);
        case ("delete"):
            return new DeleteCommand(index);
        }

        if (line.equals("todo") || line.equals("deadline") || line.equals("event") || line.equals("find")) {
            if (doneLine.length == 1) {
                throw new DukeException("EmptyDescription", line);
            }
        }

        try {
            switch (line) {
            case ("find"):
                return new FindCommand(doneLine[1]);
            case ("todo"):
                return new AddCommand(new Todo(doneLine[1]));
            case ("deadline"):
                String[] info = doneLine[1].split(" /by ");
                return new AddCommand(new Deadlines(info[0], info[1]));
            case ("event"):
                String[] info2 = doneLine[1].split(" /at ");
                String[] information = Events.parseEvent(info2[1]);
                return new AddCommand(new Events(info2[0], information[0],
                        information[1], information[2]));
            default:
                throw new DukeException("UnknownCommand");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("EmptyDetails", line);
        }
    }
}

