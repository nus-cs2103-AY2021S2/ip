package alice;

import alice.task.TaskList;

/**
 * This class is the representation of the agent being created. this class is
 * immutable and any method calls from this class should not have any side
 * effects. Message returned to the user should be stored in the agent and
 * output of it should be handled elsewhere.
 */

public class Alice {
	public static final String GREETING = "Hello!";

	private final boolean isDone;
	private final boolean hasDelta;
	private final String currentMessage;
	private final TaskList data;
	private final Alice previous;

	/**
	 * Constructs a new agent specifying tasks and if it's done.
	 *
	 * @param newData The tasks to be specified.
	 * @param isDone If the agent is done operating.
	 */
	public Alice(TaskList newData, boolean isDone) {
		this.currentMessage = String.format("%s\nInitialized with %d tasks",
				GREETING, newData.count());
		this.data = newData;
		this.isDone = isDone;
		this.hasDelta = false;
		this.previous = null;
	}

	/**
	 * Constructs a new agent specifying the response, tasks, if it's done, and whether
	 * it has changed from some previous state.
	 *
	 * @param currentMessage The agent's response.
	 * @param newData The tasks to be specified.
	 * @param isDone If the agent is done operating.
	 * @param hasDelta If the data in the agent has changed from some other instance.
	 */
	public Alice(String currentMessage, TaskList newData, boolean done, boolean hasDelta, Alice previous) {
		this.currentMessage = currentMessage;
		this.data = newData;
		this.isDone = isDone;
		this.hasDelta = hasDelta;
		this.previous = previous;
	}

	/**
	 * Returns if the agent is still operating.
	 *
	 * @return True if the agent is no longer expecting any command otherwise false.
	 */
	public boolean getIsDone() {
		return isDone;
	}

	/**
	 * Returns if the agent data has been changed.
	 *
	 * @return True if certain operation has altered the data of the agent.
	 */
	public boolean hasDelta() {
		return hasDelta;
	}

	/**
	 * Returns the tasks stored in the agent.
	 *
	 * @return data stored in the agent.
	 */
	public TaskList getData() {
		return this.data;
	}

	public Alice getPrevious() {
		return this.previous;
	}

	/**
	 * Returns the message that the agent wants to show to the user.
	 *
	 * @return The response from the agent.
	 */
	public String getCurrentMessage() {
		return currentMessage;
	}
}
