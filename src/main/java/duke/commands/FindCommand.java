package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the find command of the user to search tasks based on keyword.
 * Format of command: "find [keyword]".
 */
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.printIndentOutput("The current list is empty.");
        } else {
            ArrayList<Integer> indexList = new ArrayList<>(tasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTask(i).getTaskName().contains(keyword)) {
                    indexList.add(i);
                }
            }
            if (indexList.size() <= 0) {
                ui.printIndentOutput("No tasks found with the given keyword.");
            } else {
                ui.printIndentOutput("Here are the matching tasks in you list:");
                for (int i = 0; i < indexList.size(); i++) {
                    ui.printIndentOutput((i + 1) + ". " + tasks.getTask(indexList.get(i)));
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
