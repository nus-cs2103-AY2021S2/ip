class Task {
	private String name;
	private boolean isDone;

	Task(String s) {
		this.name = s;
		this.isDone = false;
	}

	Task(String s, boolean b) {
		this.name = s;
		this.isDone = b;
	}

	void finish() {
		this.isDone = true;
	}

	String getName() {
		return this.name;
	}

	boolean getIsDone() {
		return this.isDone;
	}

	String saveName() {
		return String.format("task | %s | %b", this.name, this.isDone);
	}

	public String toString() {
		String check = " ";
		if (this.isDone) {
			check = "X";
		}
		return String.format("[%s]  %s", check, this.name);
	}
}
