package chat.command;

import chat.TaskList;
import chat.Storage;
import chat.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);
    }
    
}
