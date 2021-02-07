package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        int numberOfTasks = tasks.size();
        for (int i = 0; i < numberOfTasks; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(arguments)) {
                String foundTaskMessage = String.format("%d.%s\n", i, task);
                message.append(foundTaskMessage);
            }
        }
        if (message.length() != 0) {
            ui.showNewLine("Here are the matching tasks in your list:");
            ui.show(message.toString());
        } else {
            ui.showNewLine("No found tasks matching your query.");
        };

    }
}
