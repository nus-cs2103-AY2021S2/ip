public class Event extends Task{
    private final String deadline;

    Event(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    Event(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    @Override
    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Event(this.task, this.deadline, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.deadline + ")";
    }
}
