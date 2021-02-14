package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.FileManager;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

/**
 * Displays help page.
 */
public class HelpCommand extends Command{
    private FileManager helpFile;
    public HelpCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes help command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return                 {@code CommandResponse} after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        String helpText = "";
        switch(this.content){
        case "todo":
            helpFile = new FileManager("/help/todo.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "deadline":
            helpFile = new FileManager("/help/deadline.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "event":
            helpFile = new FileManager("/help/event.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "list":
            helpFile = new FileManager("/help/list.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "finish":
            helpFile = new FileManager("/help/finish.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "delete":
            helpFile = new FileManager("/help/delete.txt");
            helpText = helpFile.readResourcesText();
            break;
        case "find":
            helpFile = new FileManager("/help/find.txt");
            helpText = helpFile.readResourcesText();
            break;
        default:
            helpFile = new FileManager("/help/commands.txt");
            helpText = helpFile.readResourcesText();
        }

        return new CommandResponse(helpText, false);
    }
}
