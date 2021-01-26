public class Deadline extends Task {
	public final String dateTime;

	public Deadline(String description, String dateTime) {
		super(false, description);
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dateTime);
	}

	@Override
	public String encode() {
		return String.format("D | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.dateTime);
	}
}
