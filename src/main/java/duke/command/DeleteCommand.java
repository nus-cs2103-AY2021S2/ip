package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.exception.DukeException;
import duke.exception.DukeCommandException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeCommandException {
        if(taskManager.getTasksSize() == 0){
            throw new DukeCommandException("delete", String.valueOf(index), "There are no task to be deleted.");
        } else if(index < 0 || index >= taskManager.getTasksSize()) {
            throw new DukeCommandException("delete", String.valueOf(index), "Please enter a valid task index ranging " +
                    "from 1 to " + taskManager.getTasksSize() + " (inclusive).");
        } else {
            try {
                Task task = taskManager.deleteTask(index);
                ui.printDeleteMsg(task, taskManager.getTasksSize());
                Storage.saveTasks(taskManager.getTasks());
            } catch(DukeException e) {
                throw new DukeCommandException("delete", String.valueOf(index), e.getMessage());
            }
        }
    }
}
