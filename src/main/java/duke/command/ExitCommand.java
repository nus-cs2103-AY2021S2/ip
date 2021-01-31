package duke.command;

import duke.Storage;
import duke.TaskManager;

public class ExitCommand extends Command {

    /**
     *  ExitCommand constructor.
     */
    public ExitCommand() {
        //do nothing
    }

    /**
     *  Executes ExitCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     */
    public String execute(TaskManager tm, Storage st) {
        return "Thanks for using me!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
