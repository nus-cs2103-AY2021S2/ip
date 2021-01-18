public class Deadline extends Task {

    Deadline(String task) {
        super(task);
    }

    Deadline(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Deadline(this.task, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
