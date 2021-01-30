package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.task.TaskList;

/**
 * Models a possible user command to the Duke program; commands are of type
 * 'ADD', 'DELETE',
 */
public abstract class Command {
    public enum CommandName {
        TODO, DEADLINE, EVENT, LIST, DONE, FIND, DELETE, BY, AT, ON, EMPTY, INVALID, BYE
    };

    protected CommandName commandName;
    protected String argument;
    protected String[] argumentTokens;

    public static CommandName whichCommand(String token) {
        for (CommandName cmdname : CommandName.values()) {
            if (token.equalsIgnoreCase(cmdname.toString())) {
                return cmdname;
            } else if (token.isBlank()) {
                return CommandName.EMPTY;
            }
        }
        return CommandName.INVALID;
    }

    /**
     * Creates a {@link Command}, with a {@link String}[] that contains the
     * tokenised arguments to the command, from the user input.
     * 
     * @param commandString the tokenised input from the user, as arguments to the
     *                      command
     */
    public Command(String... argumentTokens) {
        this.argumentTokens = argumentTokens;
    }

    public abstract void execute(TaskList tasks, Storage storage)
            throws DukeEmptyDetailException, DukeInvalidDateException;
}
