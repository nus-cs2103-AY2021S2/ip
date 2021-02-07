package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the execution of Delete Command
 */
public class DeleteCmd extends Command {
    private final String cmdArgs;

    public DeleteCmd(String cmdArgs) {
        this.cmdArgs = cmdArgs;
    }

    /**
     * Returns the response of the bot after execution of command
     *
     * @param lst TaskList
     * @return response
     */
    @Override
    public String execute(TaskList lst) {
        assert lst != null : "TaskList parameter should not be null";

        int idx = Integer.parseInt(cmdArgs) - 1;
        if (idx < 0 || idx >= lst.size()) {
            throw new DukeException(String.format("Item no. %d cannot be found in list", idx + 1));
        }

        Task t = lst.remove(idx);
        return "Noted. I've removed this task:\n"
            + String.format("\t%s\n", t.toString())
            + String.format("Now you have %d tasks in the list\n", lst.size());
    }
}
