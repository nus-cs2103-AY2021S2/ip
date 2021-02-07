package alice.command;

import alice.Alice;

import java.util.Arrays;

public abstract class Command {

	/** Tokens in the command **/
	protected String[] tokens;

	/**
	 * Class constructor.
	 */
	public Command() {
		this.tokens = new String[]{};
	}

	/**
	 * Class constructor specifying the tokens in the command.
	 *
	 * @param tokens
	 */
	public Command(String[] tokens) {
		this.tokens = tokens;
	}

	/**
	 * Returns the tokens in the command.
	 *
	 * @return Tokens in the command.
	 */
	public String[] getTokens() {
		return this.tokens;
	}

	/**
	 * Returns the new state of the agent after execution of command.
	 *
	 * @param agent The current state of the agent.
	 * @return The new state of the agent
	 */
	public abstract Alice execute(Alice agent);

	/**
	 * {@inheritDoc}
	 */
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
