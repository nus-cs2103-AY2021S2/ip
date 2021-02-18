package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline represents a deadline, which is a type of task that ChaeLisa can take note.
 * Deadline has 2 main components:
 * the Deadline description: taskName
 * the Deadline due date: dlTime
 */
class Deadline extends Task {
    private final LocalDateTime deadlineTime;

    /**
     * Returns a Deadline with specified description (name) and due date.
     *
     * @param taskName     the Deadline description
     * @param deadlineTime the Deadline due date
     */
    public Deadline(String taskName, LocalDateTime deadlineTime) {
        super(taskName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns a String, which is the expression of a Deadline.
     *
     * @return Deadline expression
     */
    @Override
    public String printTask() {
        String ans;
        if (this.isTaskDone()) {
            ans = "[D][X] " + this.getTaskName() + " (by: "
                    + this.deadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else if (!this.isTaskDone()) {
            ans = "[D][  ] " + this.getTaskName() + " (by: "
                    + this.deadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "Error";
        }
        assert (!ans.equals("Error")) : "The task should be either done or not";

        return ans;
    }

    @Override
    public boolean isTaskDone() {
        return super.isTaskDone();
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }


    /**
     * Returns the deadline of this Deadline object in the correct format.
     *
     * @return the deadline of this Deadline object
     */
    public LocalDateTime getDeadlineTime() {
        return deadlineTime;
    }
}
