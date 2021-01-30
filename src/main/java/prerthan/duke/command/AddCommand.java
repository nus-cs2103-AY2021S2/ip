package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.task.TaskList;
import prerthan.duke.task.Todo;
import prerthan.duke.task.Deadline;
import prerthan.duke.task.Event;

/**
 * AddCommand
 */
public class AddCommand extends Command {
    private Command.CommandName commandName;

    public AddCommand(String detail, CommandName commandName) {
        super(detail);
        this.commandName = commandName;
    }

    public AddCommand(String detail, String timeString, CommandName commandName) {
        super(detail, timeString);
        this.commandName = commandName;
    }

    public AddCommand(String detail, String startString, String endString, CommandName commandName) {
        super(detail, startString, endString);
    }

    @Override
    public void execute(final TaskList tasks, final Storage storage)
            throws DukeEmptyDetailException, DukeInvalidDateException {
        switch (this.commandName) {
        case TODO:
            tasks.addTask(new Todo(this.argumentTokens[0]));
        case EVENT:
            tasks.addTask(new Event(this.argumentTokens[0], this.argumentTokens[1], this.argumentTokens[2]));
            break;
        case DEADLINE:
            tasks.addTask(new Deadline(this.argumentTokens[0], this.argumentTokens[1]));
            break;
        default:
            break;
        }
    }
}