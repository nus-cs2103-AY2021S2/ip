public class Events extends DukeTask {
    private final String time;

    public Events(String name, String time) {
        super(name, TaskType.EVENT);
        this.time = time;
    }

    public Events(String name, boolean isDone, String time) {
        super(name, isDone, TaskType.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.time);
    }

    @Override
    public String formatDuke() {
        return super.formatDuke() + " | " + this.time;
    }

    @Override
    public DukeTask markDone() {
        return new Events(this.name, true, this.time);
    }
}

