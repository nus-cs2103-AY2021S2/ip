package alice.command;

import alice.Alice;

public class CommandBye extends Command {

	public CommandBye() {
		this.tokens = new String[]{};
	}

	public CommandBye(String[] tokens) {
		super(tokens);
	}

	@Override
	public Alice execute(Alice agent) {
		return new Alice(Alice.BYE, agent.getData(), true, false);
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
