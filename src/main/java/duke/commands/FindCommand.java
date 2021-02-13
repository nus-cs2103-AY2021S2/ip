package duke.commands;

import duke.message.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keywords;

    public FindCommand(String keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        assert storage != null;

        return Messages.getFindTaskMessage(tasks.filter(keywords));
    }
}
