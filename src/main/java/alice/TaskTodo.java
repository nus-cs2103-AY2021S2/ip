package alice;

public class TaskTodo extends Task {

	public TaskTodo(String name) {
		super(name);
	}

	public TaskTodo(String name, boolean done) {
		super(name, done);
	}

	public TaskTodo setDone(boolean done) {
		return new TaskTodo(this.name, done);
	}

	@Override
	public TaskTodo clone() {
		return new TaskTodo(this.name, this.done);
	}

	@Override
	public String toString() {
		return String.format("[T]%s", super.toString());
	}
}
