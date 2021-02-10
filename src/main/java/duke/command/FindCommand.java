package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return ui.getNoTaskExistsMessage();
        }

        final List<String> matchingTasks = tasks.find(keyword);
        assert(matchingTasks != null);
        return ui.getFindTaskMessage(matchingTasks);

    }

}
