package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline represents a deadline, which is a type of task that Danh's Duke can take note.
 * Deadline has 2 main components:
 * the Deadline description: taskName
 * the Deadline due date: dlTime
 */
class Deadline extends Task {
    public LocalDateTime dlTime;

    /**
     * Returns a Deadline with specified description (name) and due date.
     *
     * @param taskName the Deadline description
     * @param dlTime   the Deadline due date
     */
    public Deadline(String taskName, LocalDateTime dlTime) {
        super(taskName);
        this.dlTime = dlTime;
    }

    /**
     * Returns a String, which is the expression of a Deadline.
     *
     * @return Deadline expression
     */
    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[D][X] " + this.taskName + " (by: "
                    + this.dlTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "[D][ ] " + this.taskName + " (by: "
                    + this.dlTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        return ans;
    }
}
