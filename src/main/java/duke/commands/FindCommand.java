package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represent a user command to find tasks in the task list that match keywords.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keywords;

    /**
     * Constructor takes in the keywords to match.
     * @param keywords The keywords to match.
     */
    public FindCommand(String keywords) {
        super();
        this.keywords = keywords;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        return Messages.getFindTaskMessage(tasks.filter(keywords));
    }
}
