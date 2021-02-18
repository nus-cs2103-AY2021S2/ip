package duke.utils;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ClearCommand;
import duke.exceptions.DukeException;
import duke.type.CommandType;
import duke.ui.ErrorBox;
import duke.ui.Ui;


/**
 * This class extracts and processes the user input and produce command to be executed after parsing.
 */
public class Parser {
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * This method processes all the user input and create respective command to be executed.
     *
     * @return the respective command to be executed.
     */
    public final Command parse() {
        String[] validInputs = extractValidInput();
        if (validInputs[0] == null) {
            return new ErrorCommand();
        }

        String instruction = validInputs[0];
        String taskName = validInputs[1];
        String date = validInputs[2];
        Command command;
        switch (instruction) {
        case "bye":
            command = new ExitCommand();
            TaskStorage.writeToFiles();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(taskName);
            break;
        case "todo": case "deadline": case "event":
            command = new AddCommand(instruction, taskName, date);
            break;
        case "delete":
            command = new DeleteCommand(taskName);
            break;
        case "find":
            command = new FindCommand(taskName);
            break;
        case "clear":
            command = new ClearCommand();
            break;
        default:
            command = new ErrorCommand();
            break;
        }
        return command;
    }


    /**
     * extracts the command key in by user.
     *
     * @param input the input key in by user.
     * @return String representation of the command word of the user input.
     */
    static String extractInstruction(String input) throws DukeException {
        // Split by the first space encountered.
        String[] splits = input.trim().toLowerCase().split(" ", 2);
        assert splits.length >= 1 : "Command cannot be empty!";
        String instruction = splits[0];
        //If the instruction is empty then throw exception.
        if (input.replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.EMPTY_COMMAND);
        }

        // If the instruction is not any of the known instructions then throw exception.
        if (CommandType.valueOfType(instruction) == null) {
            throw new DukeException(Ui.COMMAND_ERROR);
        }

        return instruction;

    }

    /**
     * extracts task name from user input.
     *
     * @param input   user input.
     * @param command user command.
     * @return the task name if there is one and return empty string if task name empty.
     */
    static String extractTask(String input, String command) throws DukeException {

        // Remove instruction from the user Input.
        String taskInput = input.replaceAll(command, "").trim();
        String noTask = "";
        boolean isEmptyTask = taskInput.equals(noTask);
        switch (command) {
        case "todo": case "find": case "done": case "delete":
            if (isEmptyTask) {
                throw new DukeException(Ui.EMPTY_TASK);
            }
            return taskInput;
        case "deadline": case "event":
            if (isEmptyTask) {
                throw new DukeException(Ui.EMPTY_TASK);
            }
            return taskInput.split("/")[0];
        case "bye": case "list": case "clear":
            if (taskInput.length() > 0) {
                throw new DukeException(Ui.COMMAND_ERROR);
            }
            return noTask;
        default:
            return noTask;
        }
    }

    /**
     * This method extracts the date of the task to be done.
     *
     * @param input user input.
     * @param instruction user instruction.
     * @return the task date in String and return empty if there is no date.
     */
    static String extractDate(String input, String instruction) throws DukeException {

        // Remove instruction from the user input to get taskInput.
        String taskInput = input.replaceAll(instruction, "").trim();

        // Split task input by the first / encountered.
        String[] splitParts = taskInput.split("/", 2);

        boolean hasDate = splitParts.length == 2;
        String noDate = "";
        if (!hasDate) {
            if (instruction.equals("deadline")
                    || instruction.equals("event")) {
                throw new DukeException(Ui.MISSING_DATE);
            }
            // if the instruction does not require date return no date.
            return noDate;
        }

        // Convert the date format.
        String date = DateAndTime.convertDateFormat(splitParts[1]);
        if (date.equals(Ui.WRONG_DATE_FORMAT)) {
            throw new DukeException(Ui.WRONG_DATE_FORMAT);
        }
        return date;
    }

    /**
     * This method extracts valid input from the user input.
     * @return a String array that store instruction, taskName and date.
     */
    private String[] extractValidInput() {
        String instruction;
        String taskName;
        String date;
        String[] validInputs = new String[3];
        try {
            instruction = extractInstruction(input);
            taskName = extractTask(input, instruction);
            date = extractDate(input, instruction);
            validInputs[0] = instruction;
            validInputs[1] = taskName;
            validInputs[2] = date;
        } catch (DukeException e) {
            ErrorBox.display(e.getMessage());
        }

        return validInputs;
    }

}
