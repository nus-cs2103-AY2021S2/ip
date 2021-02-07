package ekud.command;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.task.TaskWithDateTime;

/**
 * Command that lists all tasks, optionally filter by date if given.
 */
public class ListCommand extends Command {
    private final boolean toFilter;
    private LocalDate date;

    /**
     * Construct a command that lists all tasks with a tagged date equal to supplied date.
     *
     * @param date The date to be used as the filter
     */
    public ListCommand(LocalDate date) {
        toFilter = true;
        this.date = date;
    }

    /**
     * Construct a command that lists all tasks.
     */
    public ListCommand() {
        toFilter = false;
    }

    /**
     * Output all tasks if no date specified, tasks due on the day otherwise.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     */
    @Override
    public String execute(final TaskList tasks, Storage storage) throws EkudException {
        // list command with no arguments
        if (!toFilter) {
            if (tasks.isEmpty()) {
                return "I know nothing, feed me with something :)";
            }

            String reply = "Get working! You need finish these things:";
            StringBuilder replyBuilder = new StringBuilder("Get working! You need finish these things:");
            AtomicInteger atomicIndex = new AtomicInteger(0);
            tasks.forEach(task -> replyBuilder.append(System.lineSeparator())
                    .append(String.format("  %d. %s", atomicIndex.incrementAndGet(), task.toString())));
            return replyBuilder.toString();
        }

        // list tasks due on a particular day
        StringBuilder replyBuilder = new StringBuilder();
        tasks.stream()
                .filter(task -> task instanceof TaskWithDateTime)
                .filter(task -> ((TaskWithDateTime) task).getDateTime().toLocalDate().equals(date))
                .forEach(task -> replyBuilder.append(System.lineSeparator()).append(task.toString()));

        if (replyBuilder.length() == 0) {
            return "You're free for the day!";
        } else {
            return replyBuilder.insert(0, System.lineSeparator())
                    .insert(0, "You have these deadlines/events:")
                    .toString();
        }
    }
}
