package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.getTasks().size() == 0) {
            ui.printMessage("There are no tasks in your list. Time to add some!");
        } else {
            int matchingResults = 0;
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if (tasks.getTasks().get(i).getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                    output += (i + 1)+". " + tasks.getTasks().get(i) + "\n";
                    matchingResults++;
                }
            }
            if (matchingResults == 0) {
               ui.printMessage("There are no matching tasks with that keyword.");
            } else {
                ui.printMessage(output);
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
