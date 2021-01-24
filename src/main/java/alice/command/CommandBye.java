package alice.command;

import alice.Alice;

public class CommandBye extends Command {

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
		return new Alice(Alice.BYE, agent.getData(), true, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
