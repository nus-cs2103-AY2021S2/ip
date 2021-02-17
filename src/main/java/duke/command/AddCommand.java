package duke.command;

import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.MessageGenerator;

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
