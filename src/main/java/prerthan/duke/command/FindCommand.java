package prerthan.duke.command;

import prerthan.duke.Duke;
import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.io.Output;
import prerthan.duke.io.Storage;
import prerthan.duke.task.Task;
import prerthan.duke.task.TaskList;

import java.util.stream.Stream;

/**
 * FindCommand
 */
public class FindCommand extends Command {
    public FindCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);

        if (argumentTokens.length != 1) {
            throw new DukeInvalidArgumentException("Find command should have only one word as argument.",
                argumentTokens, commandName, this.getClass().getSimpleName());
        }
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output)
        throws DukeEmptyDetailException, DukeInvalidDateException {
        Duke.output.add(String.format("These tasks match or contain the keyword '%s':", argumentTokens[0]));
        Duke.output.say(
            Stream.of(Duke.tasks.findTasksWithKeyword(argumentTokens[0])).map(Task::toString).toArray(String[]::new));

    }
}