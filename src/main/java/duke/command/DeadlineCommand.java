package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a 'deadline' command.
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (getArguments().isBlank()) {
            throw new DukeException("I apologize, please input description and time for 'deadline'.");
        } else {
            String[] split = getArguments().split("/by");
            if (getArguments().equals(split[0])) {
                throw new DukeException("I apologize, please use '/by' argument to specify time for 'deadline'.");
            } else {
                Task newTask = new Deadline(split[0].strip(), split[1].strip());
                taskList.add(newTask);
                storage.addToFile(newTask);
                ui.print("Added to to-do list: \n" + newTask);
            }
        }
    }
}
