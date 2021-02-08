package duke.commands.factory;

import duke.commands.DukeCommand;
import duke.commands.DukeCommandAdd;
import duke.commands.DukeCommandBye;
import duke.commands.DukeCommandDelete;
import duke.commands.DukeCommandDone;
import duke.commands.DukeCommandFind;
import duke.commands.DukeCommandList;
import duke.exceptions.DukeExceptionCommandNotFound;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public final class DukeCommandFactory {

    private DukeCommandFactory() {}

    /**
     * Returns command based on user input.
     *
     * Factory method for command subclass generation. The first-hop parsing occurs here.
     * The rest of the parsing is offloaded to the individual parsing classes.
     *
     * @param line User input string.
     * @return DukeCommand.
     * @throws DukeExceptionCommandNotFound When command supplied is invalid.
     * @throws DukeExceptionIllegalArgument When task parsing error occurs.
     */
    public static DukeCommand getDukeCommand(String line)
            throws DukeExceptionCommandNotFound, DukeExceptionIllegalArgument {
        line = line.strip(); // input sanitization
        String arg = "";
        String cmd = line;
        if (line.contains(" ")) {
            int splitIdx = line.indexOf(' ');
            cmd = line.substring(0, splitIdx); // automatically stripped
            arg = line.substring(splitIdx+1).strip();
        }
        cmd = cmd.toLowerCase();

        Task task;
        switch (cmd) {
        case "bye":
            return new DukeCommandBye();
        case "list":
            return new DukeCommandList(arg);
        case "done":
            return new DukeCommandDone(arg);
        case "delete":
            return new DukeCommandDelete(arg);
        case "event":
            task = Event.parse(arg);
            return new DukeCommandAdd(task);
        case "todo":
            task = Todo.parse(arg);
            return new DukeCommandAdd(task);
        case "deadline":
            task = Deadline.parse(arg);
            return new DukeCommandAdd(task);
        case "find":
            return new DukeCommandFind(arg);
        default:
            throw new DukeExceptionCommandNotFound("Command '" + cmd + "' is invalid. Valid commands:"
                    + "\nbye, list, done, delete, event, todo, deadline");
        }
    }
}
