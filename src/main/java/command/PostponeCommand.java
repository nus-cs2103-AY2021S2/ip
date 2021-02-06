package command;

import entry.Deadline;
import entry.Event;
import oracle.TaskList;
import oracle.Ui;
import entry.Task;

import java.time.LocalDateTime;

public class PostponeCommand implements Command {
    private final int taskIndex;
    private final int value;
    private final String measure;

    /**
     * @param taskIndex:
     * @param value:
     * @param measure:
     */
    public PostponeCommand(int taskIndex, int value, String measure) {
        this.taskIndex = taskIndex;
        this.value = value;
        this.measure = measure;
    }

    /**
     * @param ui:    helper to print the new deleted task
     * @param tasks: we call delete on the indicated task in this TaskList
     * @return true: tells Oracle whether the loop should be terminated
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task t;
        try {
            t = tasks.get(this.taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showFormatException("PostponeCommand");
            return true;
        }
        if (t instanceof Event) {
            Event e = (Event) t;
            switch (measure) {
            case "mins":
                e.setTime(e.getTime().plusMinutes(this.value));
                break;
            case "hrs":
                e.setTime(e.getTime().plusHours(this.value));
                break;
            case "days":
                e.setTime(e.getTime().plusDays(this.value));
                break;
            case "weeks":
                e.setTime(e.getTime().plusWeeks(this.value));
                break;
            default: // value is not one of the accepted values
                ui.showFormatException("PostponeCommand");
                return true;
            }
            ui.showPostponedTask(this.taskIndex, e);
            return true;
        } else if (t instanceof Deadline) {
            Deadline e = (Deadline) t;
            switch (measure) {
            case "mins":
                e.setDeadline(e.getDeadline().plusMinutes(this.value));
                break;
            case "hrs":
                e.setDeadline(e.getDeadline().plusHours(this.value));
                break;
            case "days":
                e.setDeadline(e.getDeadline().plusDays(this.value));
                break;
            case "weeks":
                e.setDeadline(e.getDeadline().plusWeeks(this.value));
                break;
            default: // value is not one of the accepted values
                ui.showFormatException("PostponeCommand");
                return true;
            }
            ui.showPostponedTask(this.taskIndex, e);
            return true;
        } else { // the task selected is a todo, which cannot be postponed
            ui.showFormatException("PostponeCommand");
            return true;
        }
    }
}
