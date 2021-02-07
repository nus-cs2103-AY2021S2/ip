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
            return "\tHmm... You do not have any tasks!\n";
        }

        final List<String> matchingTasks = tasks.find(keyword);

        if (matchingTasks.size() == 0) {
            return "\tHmm... You do not have any matching tasks!\n";
        }

        String headerText = "\tHere are the matching tasks in your list:\n";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : matchingTasks) {
            sb.append(String.format("\t%d. %s\n", ++i, str));
        }
        return headerText + sb.toString();
    }

}
