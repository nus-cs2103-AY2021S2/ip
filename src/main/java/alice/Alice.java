package alice;

import alice.task.TaskList;

public class Alice {

	public static final String GREETING = "Hello!";

	private final boolean done;
	private final boolean hasDelta;
	private final String currentMessage;
	private final TaskList data;

	public Alice(TaskList newData, boolean done) {
		this.currentMessage = String.format("Initialized with %d tasks", newData.count());
		this.data = newData;
		this.done = done;
		this.hasDelta = false;
	}

	public Alice(String currentMessage, TaskList newData, boolean done, boolean hasDelta) {
		this.currentMessage = currentMessage;
		this.data = newData;
		this.done = done;
		this.hasDelta = hasDelta;
	}

	public static String getPrompt() {
		return ">";
	}

	public boolean getDone() {
		return done;
	}

	public boolean hasDelta() {
		return hasDelta;
	}

	public TaskList getData() {
		return this.data;
	}

	public String getGreeting() {
		return GREETING;
	}

	private Alice processEcho(String command) {
		return new Alice(command, this.data, this.done, false);
	}

	public String getCurrentMessage() {
		return currentMessage;
	}
}
