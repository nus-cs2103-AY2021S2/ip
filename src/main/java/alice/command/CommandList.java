package alice.command;

import alice.Alice;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandList extends Command {

	/**
	 * {@inheritDoc}
	 */
	public CommandList(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		String response;
		if (agent.getData().getTasks().size() <= 0) {
			response = "Your schedule is empty";
		} else {
			response = IntStream.range(0, agent.getData().count())
					.mapToObj(i -> (i + 1) + "." + agent.getData().getTasks().get(i))
					.collect(Collectors.joining("\n"));
		}
		return new Alice(response, agent.getData(), agent.getIsDone(), false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
