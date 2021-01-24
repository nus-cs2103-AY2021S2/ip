public class Deadlines extends DukeTask {
    private final String deadline;

    public Deadlines(String name, String deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public Deadlines(String name, boolean isDone, String deadline) {
        super(name, isDone, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.deadline);
    }

    @Override
    public String formatDuke() {
        return super.formatDuke() + " | " + this.deadline;
    }

    @Override
    public DukeTask markDone() {
        return new Deadlines(this.name, true, this.deadline);
    }
}

