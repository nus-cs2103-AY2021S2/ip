package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keywords;

    public FindCommand(String keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        int numberOfTasks = tasks.size();
        String dukeResponse;
        for (int i = 0; i < numberOfTasks; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keywords)) {
                String foundTaskMessage = String.format("%d.%s\n", i, task);
                message.append(foundTaskMessage);
            }
        }
        if (message.length() != 0) {
            dukeResponse = "Here are the matching tasks in your list: \n"
                    + message.toString();
        } else {
            dukeResponse = "No found tasks matching your query.\n";
        }
        return dukeResponse;
    }
}
