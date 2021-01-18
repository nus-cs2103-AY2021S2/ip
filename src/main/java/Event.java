public class Event extends Task{

    Event(String task) {
        super(task);
    }

    Event(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Event(this.task, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
