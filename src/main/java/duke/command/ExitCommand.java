package duke.command;

import duke.DukeException;
import duke.TaskList;

public class ExitCommand extends Command {

    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Test usage: this is an EXIT command";
    }
}
