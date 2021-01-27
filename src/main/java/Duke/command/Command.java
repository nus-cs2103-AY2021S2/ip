package Duke.command;

import java.util.function.Function;

public abstract class Command {
	private final String instruction;
	private final String task;
	private final String date;
	private final Function<Command, Boolean> func;

	public Command(String instruction, String task, String date, Function<Command, Boolean> func) {
		this.instruction = instruction;
		this.task = task;
		this.date = date;
		this.func = func;
	}

	public Boolean execute() {
		return func.apply(this);
	}

	public String getInstruction() {
		return instruction;
	}

	public String getTask() {
		return task;
	}

	public String getDate() {
		return date;
	}
}
