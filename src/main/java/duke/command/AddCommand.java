package duke.command;

import duke.Exceptions.DukeStorageException;
import duke.Model.TaskList;
import duke.Storage.Storage;
import duke.Tasks.Task;
import duke.Ui.MessageGenerator;

public class AddCommand extends Command {
    private final Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded) {
        this.taskToBeAdded = taskToBeAdded;
    }

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException {
        tasks.insertIntoSortedPosition(taskToBeAdded);
        storage.saveTasks(tasks);
        String addMessage = messageGenerator.generateAddMessage(taskToBeAdded, tasks);
        return new CommandResult(addMessage, false);
    }

}
