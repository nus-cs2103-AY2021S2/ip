package duketask;

import java.time.LocalDateTime;

public class TaskStub extends Task {
    public TaskStub (String description, boolean isDone, LocalDateTime createdDateTime)
            throws Task.EmptyDescriptionException {

        super(description, isDone, createdDateTime);
    }
}