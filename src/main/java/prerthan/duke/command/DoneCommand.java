package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * DoneCommand
 */
public class DoneCommand extends Command
{
    private int position;
    
    DoneCommand(String[] commandTokens, int position) throws DukeInvalidArgumentException
    {
        super(commandTokens);
        this.position = position;
        
        if (this.argumentTokens.length != 2 || !this.argumentTokens[1].matches("\\d+"))
            throw new DukeInvalidArgumentException(null, DeleteCommand.class.getSimpleName());
    }
    
    /**
     * 
     * @param commandTokens
     * @throws DukeInvalidArgumentException
     */
    public DoneCommand(String[] commandTokens) throws DukeInvalidArgumentException
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