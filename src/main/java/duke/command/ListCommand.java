package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        int numberOfTasks = tasks.size();
        String dukeResponse;
        if (numberOfTasks == 0) {
            dukeResponse = "You currently have no tasks added!\n";
        } else {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < numberOfTasks; i++) {
                String message = String.format("%d.%s", i, tasks.get(i));
                tmp.append(message);
            }
            dukeResponse = tmp.toString();
        }
        return dukeResponse;
    }
}
