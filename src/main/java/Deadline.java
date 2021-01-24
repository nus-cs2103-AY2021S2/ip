class Deadline extends Task {
	private String time;

	Deadline(String a, String b) {
		super(a);
		this.time = b;
	}

	Deadline(String a, String b, boolean c) {
		super(a, c);
		this.time = b;
	}

	@Override
	String saveName() {
		return String.format("deadline | %s | %s | %b", super.getName(), this.time, super.getIsDone());
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.time);
	}
}
