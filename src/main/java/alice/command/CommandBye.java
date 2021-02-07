package alice.command;

import alice.Alice;

public class CommandBye extends Command {

	private static final String SUCCESS_MESSAGE = "See you next time!";

	/**
	 * {@inheritDoc}
	 */
	public CommandBye() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CommandBye(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		return new Alice(SUCCESS_MESSAGE, agent.getData(), true, false, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
