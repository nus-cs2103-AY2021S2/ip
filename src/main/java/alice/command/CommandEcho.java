package alice.command;

import alice.Alice;

public class CommandEcho extends Command {
	public CommandEcho(String[] tokens) {
		super(tokens);
	}

	@Override
	public Alice execute(Alice agent) {
		return new Alice(tokens[0], agent.getData(), false, false);
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
