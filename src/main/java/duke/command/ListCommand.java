package duke.command;

import duke.storage.Storage;
import duke.model.TaskList;
import duke.ui.MessageGenerator;

public class ListCommand extends Command {

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        String listMessage = messageGenerator.getDisplayOfAllTasks(tasks);
        return new CommandResult(listMessage,false);
    }
}
