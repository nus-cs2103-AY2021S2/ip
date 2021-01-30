package ekud.command;

import java.time.LocalDate;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;
import ekud.task.TaskWithDateTime;

/**
 * Command that lists all tasks, optionally filter by date if given.
 */
public class ListCommand extends Command {
    private final boolean filter;
    private LocalDate date;

    /**
     * Construct a command that lists all tasks with a tagged date equal to supplied date.
     *
     * @param date The date to be used as the filter
     */
    public ListCommand(LocalDate date) {
        filter = true;
        this.date = date;
    }

    /**
     * Construct a command that lists all tasks.
     */
    public ListCommand() {
        filter = false;
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
        if (!filter) {
            if (tasks.isEmpty()) {
                return "I know nothing, feed me with something :)";
            }

            String reply = "Get working! You need finish these things:";
            for (int i = 0; i < tasks.size(); i++) {
                reply = String.join(System.lineSeparator(),
                        reply,
                        String.format("  %d. %s", i + 1, tasks.get(i).toString()));
            }
            return reply;
        }

        // list tasks due on a particular day
        String reply = "";
        for (Task task : tasks) {
            if (task instanceof TaskWithDateTime) {
                TaskWithDateTime t = (TaskWithDateTime) task;
                if (t.getDateTime().toLocalDate().equals(date)) {
                    reply = String.join(System.lineSeparator(),
                            reply,
                            t.toString());
                }
            }
        }
        if (reply.isBlank()) {
            return "You're free for the day!";
        } else {
            return "You have these deadlines/events:%n" + reply;
        }
    }
}
