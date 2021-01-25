import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected enum TaskState {
        done, undone;
    }

    protected final static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");
    protected final static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
            "eee, dd MMM yyyy HH:mm a");

    protected String Description;
    protected TaskState State;
    protected LocalDateTime CreatedDateTime;

    // constructors
    public Task (int state, String description, LocalDateTime createdDateTime) {
        this.State = (state > 0 ? TaskState.done : TaskState.undone);
        this.Description = description;
        this.CreatedDateTime = createdDateTime;
    }

    public Task (String description) {
        this(0, description, LocalDateTime.now());
    }

    // accessors
    public boolean isDone () {
        return (this.State == TaskState.undone);
    }

    // mutators
    public void markAsDone () {
        switch (this.State) {
        case done:
            System.out.println("     This task has already been marked done!\n     "
                    + taskInformation());
            break;

        case undone:
            this.State = TaskState.done;
            System.out.println("     Woohoo! I've marked this task as done\n     " 
                    + taskInformation());
        }
    }

    public String taskInformation () {
        return "[" + (this.State == TaskState.done ? "X" : " ") 
                + "] " + this.Description;
    }


    public String taskParseCommand () {
        // unique parsing sequence for Task
        return (this.State == TaskState.done ? 1 : 0) + " :: " + this.Description
                + " :: " + this.CreatedDateTime.format(parseFormat);
    }
}