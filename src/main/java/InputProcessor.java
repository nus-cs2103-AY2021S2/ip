public class InputProcessor {
	private String command;
	private String taskName;
	private String date;

	public InputProcessor(String input) {
		this.command = extractCommand(input);
		this.taskName = extractTask(input, command);
		this.date = extractDate(input, command);
	}

	/**
	 * extract the command key in by user.
	 *
	 * @param input the input key in by user.
	 * @return String representation of the command word of the user input.
	 */
	private static final String extractCommand(String input) {
		return input.trim().toLowerCase().split(" ")[0];
	}

	/**
	 * extracting task name from user input.
	 * @param input user input.
	 * @param command user command.
	 * @return the task name if there is one and return empty string if task name empty.
	 */
	private static final String extractTask(String input, String command) {
		String body = input.replaceAll(command, "").trim();
		if (command.equals("todo") || command.equals("delete")) {
			return body;
		} else if (command.equals("done")) {
			return body.replaceAll("[^0-9]", "");
		} else {
			try {
				return body.split("/")[0];
			} catch (ArrayIndexOutOfBoundsException e) {
				return "";
			}
		}
	}

	/**
	 * extract the date of the task to be done.
	 * @param input user input.
	 * @param command user command.
	 * @return the task date in String and return empty if there is no date.
	 */
	private static final String extractDate(String input, String command) {
		String body = input.replaceAll(command, "").trim();
		String[] parts = body.split("/", 2);
		if (parts.length == 2) {
			return parts[1];
		} else {
			return "";
		}
	}

	public String getCommand() {
		return this.command;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getDate() {
		return date;
	}
}
