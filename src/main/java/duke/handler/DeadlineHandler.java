package duke.handler;

import java.time.LocalDateTime;

import duke.tasks.Deadline;

public class DeadlineHandler extends TaskHandler {

    public DeadlineHandler(String deadlineDes, LocalDateTime dateTimeBy) {
        super(new Deadline(deadlineDes, dateTimeBy));
    }

    public Deadline getDeadlineTask() {
        assert toAdd instanceof Deadline;
        return (Deadline) toAdd;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeadlineHandler) {
            DeadlineHandler deadlineHandler = (DeadlineHandler) obj;
            Deadline deadlineTask = deadlineHandler.getDeadlineTask();
            return toAdd.equals(deadlineTask);
        } else {
            return false;
        }
    }
}
