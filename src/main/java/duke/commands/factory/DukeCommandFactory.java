package duke.commands.factory;

import duke.commands.DukeCommand;
import duke.commands.DukeCommandAdd;
import duke.commands.DukeCommandBye;
import duke.commands.DukeCommandDelete;
import duke.commands.DukeCommandDone;
import duke.commands.DukeCommandFind;
import duke.commands.DukeCommandList;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.exceptions.DukeExceptionIllegalCommand;
import duke.parser.UserInputTokenSet;
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
     * @param tokenSet User input string.
     * @return DukeCommand.
     * @throws DukeExceptionIllegalCommand When command supplied is invalid.
     * @throws DukeExceptionIllegalArgument When task parsing error occurs.
     */
    public static DukeCommand getDukeCommand(UserInputTokenSet tokenSet)
            throws DukeExceptionIllegalCommand, DukeExceptionIllegalArgument {
        String cmd = tokenSet.get("/command");
        Task task;
        switch (cmd) {
        case "event":
            task = Event.parse(tokenSet);
            return new DukeCommandAdd(task);
        case "todo":
            task = Todo.parse(tokenSet);
            return new DukeCommandAdd(task);
        case "deadline":
            task = Deadline.parse(tokenSet);
            return new DukeCommandAdd(task);
        case "bye":
            return new DukeCommandBye();
        case "list":
            return new DukeCommandList(tokenSet);
        case "done":
            return new DukeCommandDone(tokenSet);
        case "delete":
            return new DukeCommandDelete(tokenSet);
        case "find":
            return new DukeCommandFind(tokenSet);
        default:
            throw new DukeExceptionIllegalCommand("Command '" + cmd + "' is invalid. Valid commands:"
                    + "\nbye, list, done, delete, event, todo, deadline");
        }
    }
}
