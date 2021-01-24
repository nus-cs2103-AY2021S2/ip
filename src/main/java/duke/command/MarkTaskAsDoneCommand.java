package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkTaskAsDoneCommand extends Command {
    private int taskIndex;

    public MarkTaskAsDoneCommand(String taskIndex) throws DukeException {
        if (taskIndex.isBlank()) {
            throw new DukeException("I'm not sure which task you want to mark as done!");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("I can only find a task with its index number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            boolean taskWasDone = task.isDone();
            task.markAsDone();
            ui.printMarkTaskAsDoneMessage(task, taskWasDone);
            storage.saveTasksToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I cannot find the task you are referring to.");
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
