public class Deadline extends Task {
    private final String deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    Deadline(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    @Override
    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Deadline(this.task, this.deadline,true);
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.deadline;
    }
}
