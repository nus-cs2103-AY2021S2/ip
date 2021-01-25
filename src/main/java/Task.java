class Task {
	private String name;
	private boolean isDone;

	Task(String s) {
		this.name = s;
		this.isDone = false;
	}


	Task(String s, boolean c) {
		this.name = s;
		this.isDone = c;
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
<<<<<<< HEAD
		return String.format("task | %s | %b", this.name, this.isDone);
=======
		return String.format("task1!1%s1!1%b", this.name, this.isDone);
	}

	boolean onDay(String s) {
		return !this.isDone;
>>>>>>> branch-Level-8
	}

	public String toString() {
		String check = " ";
		if (this.isDone) {
			check = "X";
		}
		return String.format("[%s]  %s", check, this.name);
	}
}
