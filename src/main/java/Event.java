class Event extends Task {
	private String time;

	Event(String a, String b) {
		super(a);
		this.time = b;
	}

	Event(String a, String b, boolean c) {
		super(a, c);
		this.time = b;
	}

	@Override
	String saveName() {
		return String.format("deadline | %s | %s | %b", super.getName(), this.time, super.getIsDone());
	}

	@Override
	public String toString() {
		return String.format("[E]%s (at: %s)", super.toString(), this.time);
	}
}
