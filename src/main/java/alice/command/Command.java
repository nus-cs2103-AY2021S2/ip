package alice.command;

import alice.Alice;

public abstract class Command {

	protected String[] tokens;

	public Command() {
		this.tokens = new String[]{};
	}

	public Command(String[] tokens) {
		this.tokens = tokens;
	}

	public String[] getTokens() {
		return this.tokens;
	}

	public abstract Alice execute(Alice agent);
}
