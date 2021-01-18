class Event extends Task {
	private String time;

	Event(String a, String b) {
		super(a);
		this.time = b;
	}

	@Override
	public String toString() {
		return String.format("[E]%s (at: %s)", super.toString(), this.time);
	}
}
