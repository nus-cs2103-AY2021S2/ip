/**
 * Class that represents the type of task, description of task
 * and whether the task is done. Subclasses may include the date
 * and time of the task.
 */

public class Task {

    private String description;
    private boolean isDone;
    private char type;

    public Task(String description, boolean isDone) throws DukeException {
        if (description.trim().isEmpty()) {
            throw new DukeException("Task cannot be empty.");
        } else {
            this.description = description;
            this.isDone = isDone;
            this.type = 0;
        }
    }

    public Task(String description) throws DukeException {
        if (description.trim().isEmpty()) {
            throw new DukeException("Task cannot be empty.");
        } else {
            this.description = description;
            this.isDone = false;
            this.type = 0;
        }
    }

    public String getStatusIcon() {
        return (isDone? "\u2713" : "\u2718");
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public char getType() {
        return this.type;
    }

    public String getData() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return task.description.equals(this.description)
                && task.type == this.type
                && task.isDone == this.isDone;
    }

    @Override
    public int hashCode() {
        // prime number 17 is used.
        int result = 17;
        result = 31 * result + description.hashCode();
        return result;
    }
}
