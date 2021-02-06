package alice.command;

import alice.Alice;
import alice.AliceException;
import alice.task.Task;
import alice.task.TaskBuilder;
import alice.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class CommandAdd extends Command {

	private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list";

	/** Usage details. **/
	private static final String USAGE_TODO = "todo usage: todo [activity]";
	private static final String USAGE_DEADLINE = "deadline usage: deadline [activity] /by [deadline yyyy/MM/dd]";
	private static final String USAGE_EVENT = "todo usage: event [activity] /at [time yyyy/MM/dd]";

	public CommandAdd(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		Alice newAgent;
		try {
			Task task = TaskBuilder.buildTask(tokens);
			List<Task> newStore = agent.getData().getTasks().stream().map(Task::clone).collect(Collectors.toList());
			newStore.add(task);
			String response = String.format(SUCCESS_MESSAGE, task, newStore.size());
			newAgent = new Alice(response, new TaskList(newStore), false, true);
		} catch (AliceException aliceException) {
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getIsDone(), false);
		} catch (IllegalStateException illegalStateException) {
			newAgent = new Alice("Something that was not supposed to happen happened", agent.getData(),
					agent.getIsDone(), false);
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
			String usage;
			switch (tokens[0]) {
			case "todo":
				usage = USAGE_TODO;
				break;
			case "deadline":
				usage = USAGE_DEADLINE;
				break;
			default:
				usage = USAGE_EVENT;
			}
			newAgent = new Alice(usage, agent.getData(), agent.getIsDone(), false);
		}
		return newAgent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
