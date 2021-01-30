package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * A user
 */
public class DeleteCommand extends Command
{
    private int position;
    
    DeleteCommand(String[] argumentTokens, int position) throws DukeInvalidArgumentException
    {
        super(argumentTokens);
        this.position = position;
        
        if (this.argumentTokens.length != 2 || !this.argumentTokens[1].matches("\\d+"))
            throw new DukeInvalidArgumentException(null, DeleteCommand.class.getSimpleName());
    }

    /**
     * 
     * @param commandTokens
     * @throws NumberFormatException
     * @throws DukeInvalidCommandException
     */
    public DeleteCommand(String[] commandTokens) throws DukeInvalidArgumentException
    {
        this(commandTokens, Integer.parseInt(commandTokens[1]));
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage)
    {
        if (!(this.position >= 1) && this.position <= tasks.numberOfTasks())
        {
            tasks.deleteTaskAtPosition(position);
        }
    }
}