package duke.command;

import duke.Storage;
import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.DukeCommandException;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    private String desc = "";
    private LocalDateTime dateTime;

    public DeadlineCommand(String desc, LocalDateTime dateTime) {
        this.desc = desc;
        this.dateTime = dateTime;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            Deadline deadline = taskManager.addDeadline(this.desc, this.dateTime);
            ui.printAddMsg(deadline, taskManager.getTasksSize());
            Storage.saveTasks(taskManager.getTasks());
        } catch (DukeException e) {
            throw new DukeCommandException("deadline", desc, e.getMessage());
        }
    }
}
