package duke.command;

import java.time.LocalDateTime;

import duke.common.DukeException;
import duke.common.DukeString;
import duke.task.TaskList;

public class SnoozeCommand implements Command {
    private final int index;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs a new SnoozeCommand with the associated index and date, to modify deadline tasks.
     *
     * @param idx the index of the deadline task to modify.
     * @param date the amended date.
     */
    public SnoozeCommand(final int idx, final LocalDateTime date) {
        this.index = idx;
        this.startDate = date;
        this.endDate = LocalDateTime.MIN;
    }

    /**
     * Constructs a new SnoozeCommand with the associated index and dates, to modify event tasks.
     * @param idx the index of the event task to modify.
     * @param startDate the amended start date.
     * @param endDate the amended end date.
     */
    public SnoozeCommand(final int idx, final LocalDateTime startDate, final LocalDateTime endDate) {
        this.index = idx;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute(TaskList taskList) {
        if (index > taskList.getSize() || index <= 0) {
            throw new DukeException.InvalidTask();
        }
        if (this.endDate == LocalDateTime.MIN) {
            return String.format(DukeString.MESSAGE_SNOOZED_TASK, taskList.snoozeDeadlineTask(index, startDate));
        }
        return String.format(DukeString.MESSAGE_SNOOZED_TASK, taskList.snoozeEventTask(index, startDate, endDate));
    }
}
