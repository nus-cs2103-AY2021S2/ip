package percy.command;

import java.util.NoSuchElementException;
import java.util.Optional;

import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND = "help";
    private static Optional<String> cmd;


    /**
     * Constructs a ByeCommand with isExit as true.
     */
    public HelpCommand(Optional<String> command) {
        super(false);
        cmd = command;
    }

    /**
     * Prints the bye message via the UI method, UI.bye().
     *
     * @param taskList The TaskList used to store the Tasks for this instance of Duke (not used in this method).
     */
    public String execute(TaskList taskList, Storage storage) {
        try {
            String command = cmd.get();
            switch (command) {
            case TodoCommand.COMMAND:
                return Ui.makeMsg(TodoCommand.USAGE_GUIDE);
            case EventCommand.COMMAND:
                return Ui.makeMsg(EventCommand.USAGE_GUIDE);
            case DeadlineCommand.COMMAND:
                return Ui.makeMsg(DeadlineCommand.USAGE_GUIDE);
            case FindCommand.COMMAND:
                return Ui.makeMsg(FindCommand.USAGE_GUIDE);
            case DeleteCommand.COMMAND:
                return Ui.makeMsg(DeleteCommand.USAGE_GUIDE);
            case DoneCommand.COMMAND:
                return Ui.makeMsg(DoneCommand.USAGE_GUIDE);
            case ListCommand.COMMAND:
                return Ui.makeMsg(ListCommand.USAGE_GUIDE);
            default:
                return Ui.makeHelpMsg();
            }
        } catch (NoSuchElementException e) {
            return Ui.makeHelpMsg();
        }
    }
}
