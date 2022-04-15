package ekud.command;

import java.util.concurrent.atomic.AtomicInteger;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {
    /**
     * Output all tasks due on the day.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     */
    @Override
    public String execute(final TaskList tasks, Storage storage) throws EkudException {
        if (tasks.isEmpty()) {
            return "I know nothing, feed me with something :)";
        }

        StringBuilder replyBuilder = new StringBuilder("Get working! You need finish these things:");
        AtomicInteger atomicIndex = new AtomicInteger(0);
        tasks.forEach(task -> replyBuilder.append(System.lineSeparator())
                .append(String.format("  %d. %s", atomicIndex.incrementAndGet(), task.toString())));

        return replyBuilder.toString();
    }
}
