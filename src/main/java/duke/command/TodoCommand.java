package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DescriptionMissingException;

public class TodoCommand extends AddCommand {
    private final String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionMissingException {
        Todo todo = getTask();
        super.addThisTask(tasks, todo, storage);
    }

    @Override
    public Todo getTask() throws DescriptionMissingException {
        String name = fullCommand.substring(4).strip();
        if (name.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            return new Todo(name);
        }
    }
}
