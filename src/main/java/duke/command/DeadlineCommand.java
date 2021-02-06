package duke.command;

import java.time.LocalDateTime;

import duke.bot.Storage;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.task.Deadline;

/** An executable command to create a task with a due date */
public class DeadlineCommand extends Command {
    /** Description of a Deadline to be created */
    private String desc = "";
    /** Date and time when a Deadline to be created is ending */
    private LocalDateTime dateTime;

    /**
     * Constructor of a DeadlineCommand
     *
     * @param desc Description of a Deadline to be created
     * @param dateTime Date and time of when a Deadline to be created is ending
     */
    public DeadlineCommand(String desc, LocalDateTime dateTime) {
        this.desc = desc;
        this.dateTime = dateTime;
    }

    /**
     * Executes the Deadline command to create a Deadline task in the list and returns a response message
     *
     * @throws DukeCommandException if there is an issue adding the Deadline into the task list or saving the tasks into
     * hard disk
     */
    @Override
    public String execute() throws DukeCommandException {
        try {
            Deadline deadline = taskManager.addDeadline(this.desc, this.dateTime);
            Storage.saveTasks(taskManager.getTasks());
            return ui.constructAddMessage(deadline, taskManager.getTasksSize());
        } catch (DukeException e) {
            throw new DukeCommandException("deadline", desc, e.getMessage());
        }
    }
}
