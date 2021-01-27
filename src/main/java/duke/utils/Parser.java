package duke.utils;

import duke.task.Task;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;

public class Parser {
	private String instruction;
	private String taskName;
	private String date;

	public Parser(String input) {
		this.instruction = extractCommand(input);
		this.taskName = extractTask(input, instruction);
		this.date = extractDate(input, instruction);
	}

	public final Command parse() {
		Command command;
		switch (instruction) {
		case "bye":
			command = new ExitCommand(taskName, date);
			TaskStorage.writeToFiles(Task.getTaskList());
			break;
		case "list":
			command = new ListCommand();
			break;
		case "done":
			command = new DoneCommand(taskName, date);
			break;
		case "todo": case "deadline": case "event":
			command = new AddCommand(instruction, taskName, date);
			break;
		case "delete":
			command = new DeleteCommand(taskName, date);
			break;
		default:
			command = new ErrorCommand();
			break;
		}
		return command;
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
			return DateAndTime.converter(parts[1]);
		} else {
			return "";
		}
	}

	public String getInstruction() {
		return this.instruction;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getDate() {
		return date;
	}
}
