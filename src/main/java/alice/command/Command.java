package alice.command;

import alice.Alice;

import java.util.Arrays;

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

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Command command = (Command) object;
		if (tokens.length != command.tokens.length) {
			return false;
		}
		for (int i = 0; i < tokens.length; i++) {
			if (!tokens[i].equals(command.tokens[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(tokens);
	}
}
