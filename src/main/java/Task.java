import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    public static class EmptyDescriptionException extends Exception {
        private static final long serialVersionUID = 1L;

        public EmptyDescriptionException () {
            super("! Task description cannot be empty.");
        }
    }

    protected enum TaskState {
        done, undone;
    }

    protected String description;
    protected TaskState state;
    protected LocalDateTime createdDateTime;

    // constructors
    public Task (String description, boolean isDone, LocalDateTime createdDateTime) 
            throws Task.EmptyDescriptionException {
        
        if (description.isEmpty()) {
            throw new Task.EmptyDescriptionException();
        }
        
        this.state = (isDone ? TaskState.done : TaskState.undone);
        this.description = description;
        this.createdDateTime = createdDateTime;
    }

    public Task (String description) throws Task.EmptyDescriptionException {
        this(description, false, LocalDateTime.now());
    }

    // accessors
    public boolean isDone () {
        return (this.state == TaskState.undone);
    }

    public String taskInformation (DateTimeFormatter outputFormat) {
        return "[" + (this.state == TaskState.done ? "X" : " ") 
                + "] " + this.description + " [ created: " 
                + this.createdDateTime.format(outputFormat) + " ]";
    }

    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        // unique parsing sequence for Task
        return (this.state == TaskState.done ? 1 : 0) + delimiter + this.description
                + delimiter + this.createdDateTime.format(parseFormat);
    }

    // mutators
    public void markAsDone (DateTimeFormatter outputFormat) {
        switch (this.state) {
        case done:
            System.out.println("     This task has already been marked done!\n     "
                    + taskInformation(outputFormat));
            break;

        case undone:
            this.state = TaskState.done;
            System.out.println("     Woohoo! I've marked this task as done\n     " 
                    + taskInformation(outputFormat));
        }
    }
}