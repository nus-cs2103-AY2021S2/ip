public class Event extends Task {
	public final String dateTime;

	public Event(String description, String dateTime) {
		super(false, description);
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.dateTime);
	}

	@Override
	public String encode() {
		return String.format("E | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.dateTime);
	}
}
