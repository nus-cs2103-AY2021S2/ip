package alice.command;

import alice.Alice;
import alice.AliceException;
import alice.Task;
import alice.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class CommandDelete extends Command {

	private static final String usage = "delete Usage: delete [index]";

	public CommandDelete(String[] tokens) {
		super(tokens);
	}

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
			String response = String.format(Alice.TASK_DELETE, agent.getData().getTasks().get(index - 1));
			newAgent = new Alice(response, new TaskList(dataList), agent.getDone(), true);
		} catch (NumberFormatException numberFormatException) {
			newAgent = new Alice("Invalid number", agent.getData(), agent.getDone(), false);
		} catch (AliceException aliceException) {
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getDone(), false);
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			newAgent = new Alice(usage, agent.getData(), agent.getDone(), false);
		}
		return newAgent;
	}
}
