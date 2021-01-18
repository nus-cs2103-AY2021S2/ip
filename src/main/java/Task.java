class Task {
	private String name;
	private boolean done;

	Task(String s) {
		this.name = s;
		this.done = false;
	}

	void finish() {
		this.done = true;
	}

	public String toString() {
		String check = " ";
		if (this.done) {
			check = "X";
		}
		return String.format("[%s]  %s", check, this.name);
	}
}
