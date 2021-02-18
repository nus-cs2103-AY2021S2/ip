package alice.command;

import alice.Alice;
import alice.AliceException;
import alice.task.Task;
import alice.task.TaskList;

import java.util.List;

public class CommandDone extends Command {

	private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n%s";

	/**
	 * Usage details.
	 **/
	private static final String USAGE = "done Usage: done [index]";

	/**
	 * {@inheritDoc}
	 */
	public CommandDone(String[] tokens) {
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
			List<Task> dataList = agent.getData().getTasks();
			dataList.set(index - 1, dataList.get(index - 1).setDone(true));
			String response = String.format(SUCCESS_MESSAGE, dataList.get(index - 1));
			newAgent = new Alice(response, new TaskList(dataList), agent.getIsDone(), true, agent);
		} catch (NumberFormatException numberFormatException) {
			newAgent = new Alice("Invalid number", agent.getData(), agent.getIsDone(), false, agent.getPrevious());
		} catch (AliceException aliceException) {
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getIsDone(), false, agent.getPrevious());
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
			newAgent = new Alice(USAGE, agent.getData(), agent.getIsDone(), false, agent.getPrevious());
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
