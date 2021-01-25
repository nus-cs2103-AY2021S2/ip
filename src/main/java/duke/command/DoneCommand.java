package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String[] commandSplit) {
        super(commandSplit);
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandSplit[1]);
            list.done(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to mark a task as done.");
        }
    }

}
