package ekud.command;

import java.time.LocalDate;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.task.TaskWithDateTime;

/**
 * Command that allows user to view all tasks tagged to a certain day.
 */
public class ViewCommand extends Command {
    private final LocalDate date;

    /**
     * Construct a command that allows user to view all tasks tagged to a certain day.
     *
     * @param date The date to match tasks
     */
    public ViewCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        StringBuilder replyBuilder = new StringBuilder();
        tasks.stream()
                .filter(task -> task instanceof TaskWithDateTime)
                .filter(task -> ((TaskWithDateTime) task).getDateTime().toLocalDate().equals(date))
                .forEach(task -> replyBuilder.append(System.lineSeparator()).append("  ").append(task.toString()));

        if (replyBuilder.length() == 0) {
            return "You're free for the day!";
        }

        return replyBuilder.insert(0, "You have these deadlines/events:").toString();
    }
}
