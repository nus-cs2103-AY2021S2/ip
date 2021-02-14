package duke.command;

import duke.Storage.Storage;
import duke.Model.TaskList;
import duke.Ui.MessageGenerator;

public class ListCommand extends Command {

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        String listMessage = messageGenerator.getDisplayOfAllTasks(tasks);
        return new CommandResult(listMessage,false);
    }
}
