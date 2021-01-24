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

	private static final String todoUsage = "todo usage: todo [activity]";
	private static final String deadlineUsage = "deadline usage: deadline [activity] /by [deadline yyyy/MM/dd]";
	private static final String eventUsage = "todo usage: event [activity] /at [time yyyy/MM/dd]";

	public CommandAdd(String[] tokens) {
		super(tokens);
	}

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
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getDone(), false);
		} catch (IllegalStateException illegalStateException) {
			newAgent = new Alice("Something that was not supposed to happen happened", agent.getData(),
					agent.getDone(), false);
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
			String usage;
			switch (tokens[0]) {
			case "todo":
				usage = todoUsage;
				break;
			case "deadline":
				usage = deadlineUsage;
				break;
			default:
				usage = eventUsage;
			}
			newAgent = new Alice(usage, agent.getData(), agent.getDone(), false);
		}
		return newAgent;
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
