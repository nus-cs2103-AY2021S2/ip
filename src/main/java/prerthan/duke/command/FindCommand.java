package prerthan.duke.command;

import java.util.stream.Stream;

import prerthan.duke.Duke;
import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.task.Task;
import prerthan.duke.task.TaskList;

/**
 * FindCommand
 */
public class FindCommand extends Command {
    public FindCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);

        if (argumentTokens.length > 1) {
            throw new DukeInvalidArgumentException("Find command must be of the format: 'find <keyword>'.",
                    this.getClass().getSimpleName());
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeEmptyDetailException, DukeInvalidDateException {
        Duke.output.add("These tasks match or contain the keyword ''");
        Duke.output.say(Stream.of(Duke.tasks.findTasksWithKeyword(argumentTokens[0])).map(Task::toString)
                .toArray(String[]::new));

    }
}