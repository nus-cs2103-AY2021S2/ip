package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a 'done' command.
 * Marks the specified task as done.
 */
public class DoneCommand extends Command {

    public DoneCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        int doneIndex = Integer.parseInt(arguments) - 1;
        Task toDo = taskList.get(doneIndex);
        toDo.doTask();
        storage.markDoneInFile(doneIndex);
        ui.print("Affirmative. The following task has been marked as done: \n" + toDo);
    }
}
