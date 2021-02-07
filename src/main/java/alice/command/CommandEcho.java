package alice.command;

import alice.Alice;

public class CommandEcho extends Command {

	/**
	 * {@inheritDoc}
	 */
	public CommandEcho(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		return new Alice(tokens[0], agent.getData(), false, false, agent.getPrevious());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
