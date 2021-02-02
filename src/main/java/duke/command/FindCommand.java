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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            System.out.println("\tHmm... You do not have any tasks!");
            return;
        }

        final List<String> matchingTasks = tasks.find(keyword);

        if (matchingTasks.size() == 0) {
            System.out.println("\tHmm... You do not have any matching tasks!");
            return;
        }

        System.out.println("\tHere are the matching tasks in your list:");
        int i = 0;
        for (String str : matchingTasks) {
            System.out.printf("\t%d. %s\n", ++i, str);
        }
    }

}
