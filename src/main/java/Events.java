public class Events extends Task {

    private String duration;

    public Events(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    public Events(String name, String duration, boolean isDone) {
        super(name, isDone);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }

}
