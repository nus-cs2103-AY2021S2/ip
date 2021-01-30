package alice.command;

import alice.Alice;
import alice.task.Task;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandFind extends Command {

	private static final String SUCCESS_MESSAGE = "Here are the matching tasks in your list:\n%s";

	private static final String USAGE = "find Usage: find [string]";

	public CommandFind(String[] tokens) {
		super(tokens);
	}

	@Override
	public Alice execute(Alice agent) {
		try {
			String searchString = tokens[1];
			if (searchString.length() == 0) {
				throw new IllegalArgumentException();
			}
			List<Task> identified = IntStream.range(0, agent.getData().count())
					.mapToObj(i -> agent.getData().getTasks().get(i))
					.filter(t -> t.getName().toLowerCase(Locale.ROOT).contains(tokens[1].toLowerCase(Locale.ROOT)))
					.collect(Collectors.toList());
			if (identified.size() <= 0) {
				return new Alice("No items found", agent.getData(), agent.getDone(), false);
			}
			String response = String.format(SUCCESS_MESSAGE,
					IntStream.range(0, identified.size())
							.mapToObj(i -> (i + 1) + " " + identified.get(i))
							.collect(Collectors.joining("\n")));
			return new Alice(response, agent.getData(), agent.getDone(), false);
		} catch (IllegalArgumentException illegalArgumentException) {
			return new Alice(USAGE, agent.getData(), agent.getDone(), false);
		}

	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
