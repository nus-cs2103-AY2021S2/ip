package duke.commands;

import java.time.LocalDateTime;

import duke.TaskList;
import duke.models.Deadline;
import duke.models.Event;

public class CommandUtils {
    /**
     * Checks whether there are other conflicting tasks with the given datetime
     * @param datetime datetime to check conflicts
     * @param tasks tasks list
     * @return whether the datetime conflicts with any tasks
     */
    public static boolean checkDateTimeConflicts(LocalDateTime datetime, TaskList tasks) {
        return tasks.toStream().anyMatch(x -> {
            if (x instanceof Event) {
                return ((Event) x).getDate().isEqual(datetime);
            } else if (x instanceof Deadline) {
                return ((Deadline) x).getDeadline().isEqual(datetime);
            }
            return false;
        });
    }
}
