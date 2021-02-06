package alice.command;

import alice.Alice;
import alice.AliceException;
import alice.task.Task;
import alice.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class CommandDelete extends Command {

	private static final String SUCCESS_MESSAGE = "I've deleted this task:\n%s";

	/** Usage details. **/
	private static final String USAGE = "delete Usage: delete [index]";

	/**
	 * {@inheritDoc}
	 */
	public CommandDelete(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		Alice newAgent;
		try {
			int index = Integer.parseInt(tokens[1]);
			if (index < 0 || index > agent.getData().count()) {
				throw new AliceException(String.format("Index %d out of bounds for schedule of size %d.",
						index, agent.getData().count()));
			}
			List<Task> dataList = agent.getData().getTasks().stream().map(Task::clone).collect(Collectors.toList());
			dataList.remove(index - 1);
			String response = String.format(SUCCESS_MESSAGE, agent.getData().getTasks().get(index - 1));
			newAgent = new Alice(response, new TaskList(dataList), agent.getIsDone(), true);
		} catch (NumberFormatException numberFormatException) {
			newAgent = new Alice("Invalid number", agent.getData(), agent.getIsDone(), false);
		} catch (AliceException aliceException) {
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getIsDone(), false);
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			newAgent = new Alice(USAGE, agent.getData(), agent.getIsDone(), false);
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
