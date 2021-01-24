package alice.command;

import alice.Alice;

public class CommandBye extends Command {

	private static final String SUCCESS_MESSAGE = "See you next time!";

	public CommandBye() {
		this.tokens = new String[]{};
	}

	public CommandBye(String[] tokens) {
		super(tokens);
	}

	@Override
	public Alice execute(Alice agent) {
		return new Alice(SUCCESS_MESSAGE, agent.getData(), true, false);
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
