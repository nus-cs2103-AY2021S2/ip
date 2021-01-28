package duke.type;

public enum CommandType {
	TODO("todo"),
	DEADLINE("deadline"),
	EVENT("event"),
	BYE("bye"),
	DONE("done"),
	LIST("list"),
	DELETE("delete");

	private String type;

	CommandType(String type) {
		this.type = type;
	}


	public static CommandType valueOfType(String type) {
		for (CommandType t : values()) {
			if (t.type.equals(type)) {
				return t;
			}
		}
		return null;
	}
}
