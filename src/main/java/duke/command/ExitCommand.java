package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        final List<String> data = tasks.encode();
        storage.saveFile(data);

        return ui.getExitMessage();
    }

}
