class Deadline extends Task {
	private String time;

	Deadline(String a, String b) {
		super(a);
		this.time = b;
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.time);
	}
}
