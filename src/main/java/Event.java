
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
    	return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), super.toString(), this.at);
    }
}
