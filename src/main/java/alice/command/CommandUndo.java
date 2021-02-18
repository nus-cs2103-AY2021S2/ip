package alice.command;

import alice.Alice;

public class CommandUndo extends Command {

	private static final String SUCCESS_MESSAGE = "Undid the previous command";

	/**
	 * {@inheritDoc}
	 */
	public CommandUndo() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CommandUndo(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		if (agent.getPrevious() == null) {
			return new Alice("Nothing to undo!", agent.getData(), false, false, agent.getPrevious());
		}
		return new Alice(SUCCESS_MESSAGE, agent.getPrevious().getData(), false, true, agent.getPrevious().getPrevious());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
