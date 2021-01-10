package models;

public class Deadline extends Todo {
    String deadline;

    public Deadline(String message, String deadline) {
        super(message);
        this.deadline = deadline;
    }

    public Deadline(String message, boolean isDone, String deadline) {
        super(message, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getMessage() {
        return String.format("[D][%s] %s (by:%s)", this.getIsDoneIcon(), this.message, this.deadline);
    }

    public Deadline markAsDone() {
        return new Deadline(this.message, true, this.deadline);
    }
}
