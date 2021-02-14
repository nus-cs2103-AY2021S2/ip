package duke.command;

import java.io.IOException;

import duke.*;

public class AddCommand extends Command {
    private final Task taskToBeAdded;

    public AddCommand(Task tasktoBeAdded) {
        this.taskToBeAdded = tasktoBeAdded;
    }


    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage)
            throws DukeStorageException{
        tasks.add(taskToBeAdded);
        storage.saveTasks(tasks);
        String addMessage = ui.generateAddMessage(taskToBeAdded, tasks);
        return new CommandResult(addMessage, false);
    }

}
