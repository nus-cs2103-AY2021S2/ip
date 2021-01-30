package sharadhr.duke.command;

import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * AddCommand
 */
public class AddCommand extends Command
{
    private CommandName commandName;
    public AddCommand(String[] commandTokens, CommandName addCommandName)
    {
        super(commandTokens);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage)
    {
        switch (this.commandName)
        {
            case TODO:
                break;
            case EVENT:
                break;
            case DEADLINE:
                break;
            default:
                break;
        }
    }
}