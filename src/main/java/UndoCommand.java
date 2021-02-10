import java.util.ArrayList;

/**
 * UndoCommand inherits Command
 * @param command the main action of the command
 * @param task is a null value for Undo Command
 * @param date is a null value for Undo Command
 */

public class UndoCommand extends Command {

    private final ArrayList<Command> pastcommands;

    public UndoCommand(String command, String task, String date, ArrayList<Command> pastCommands) {
        super(command, task, date);
        this.pastcommands = pastCommands;
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = taskList.size();
        taskList.delete(index - 1 );
        storage.rewrite(taskList);
        return ui.addUndoString();
    }

    @Override
    boolean isExit() {
        return false;
    }

}