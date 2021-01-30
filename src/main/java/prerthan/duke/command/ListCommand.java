package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * ListCommand
 */
public class ListCommand extends Command
{
    /**
     * 
     * @param commandTokens
     * @throws DukeInvalidCommandException
     */
    public ListCommand(String[] commandTokens) throws DukeInvalidArgumentException
    {
        super(commandTokens);
        
        if (commandTokens.length != 1)
            throw new DukeInvalidArgumentException(null, ListCommand.class.getSimpleName());
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage)
    {
        tasks.listTasks();
    }
}