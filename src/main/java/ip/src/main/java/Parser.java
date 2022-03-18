package ip.src.main.java;

import java.util.Locale;

/**
 * Parser is a class that parsers user input to retrieve user commands, information to create Task objects etc.
 *
 */

public class Parser {

    //static field to standardize the parsing for Edit commands.
    private static final String EDIT_HEADER = "EDIT_";

    Parser() {
    }

    /**
     * Retrieves the command entered by the user.
     *
     * @param input Input entered by user.
     * @return Returns the first word that represents the command given by the user.
     */
    public Command getCommand(String input) throws DukeException {
        String commandType = "";
        if (input.equals("bye")) {
            commandType = "bye";
        } else if (input.equals("list")) {
            commandType = "list";
        } else {
            assert input.length() > 1;
            commandType = input.split(" ")[0];
        }
        try {
            Command command = Command.valueOf(commandType.toUpperCase(Locale.ENGLISH));
            return command;
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS! I don't know what this means! :(");
        }
    }

    /**
     * Gets the specific edit type from the edit command given.
     *
     * @param input The type of edit stated by the user.
     * @return EditType command.
     * @throws DukeException
     */
    protected EditType getEditType (String input) throws DukeException {
        try {
            String editType = input.split(" ")[2];
            editType = EDIT_HEADER + editType;
            EditType edit = EditType.valueOf(editType.toUpperCase(Locale.ENGLISH));
            return edit;
        } catch (Exception e) {
            throw new DukeException("OOPS! Invalid edit type! :( \n Edit format should be: edit {index} {type of edit} {edit details}");
        }
    }

    /**
     * Gets the edit details from the edit command given.
     *
     * @param input The edit details provided by the users.
     * @return Edit details, either new content or new date and time.
     * @throws DukeException
     */
    protected String getEditDetails (String input) throws DukeException {
        try {
            input = input.split(" ", 4)[3];
            return input;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The edit details cannot be empty. \n Edit format should be: edit {index} {type of edit} {edit details}"));
        }

    }


    /**
     * Retrieves the information needed from the user input for a ToDo object.
     *
     * @param input User input.
     * @return String content needed to create a ToDo Object.
     */
    public String toDoTask(String input) throws DukeException {
        try {
            input = input.split(" ", 2)[1];
            return input;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description cannot be empty."));
        }
    }


    /**
     * Retrieves the information needed from the user input for a Event object.
     *
     * @param input User input.
     * @return String content needed to create a Event Object.
     */
    public String eventTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/at")[0];
        return content;
    }

    /**
     * Retrieves the information needed from the user input for a Event object.
     *
     * @param input User input.
     * @return String date needed to create a Event Object.
     */
    public String eventTaskAt(String input) {
        input = input.split(" ", 2)[1];
        String at = input.split("/at")[1];
        return at;
    }

    /**
     * Retrieves the information needed from the user input for a Deadline object.
     *
     * @param input User input.
     * @return String content needed to create a Deadline Object.
     */
    public String deadlineTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/by")[0];
        return content;
    }

    /**
     * Retrieves the information needed from the user input for a Deadline object.
     *
     * @param input User input.
     * @return String date needed to create a Deadline Object.
     */
    public String deadlineTaskBy(String input) {
        input = input.split(" ", 2)[1];
        String by = input.split("/by")[1];
        return by;
    }

    /**
     * Gives the ID (position) of the task to be manipulated in the TaskList.
     *
     * @param input User input.
     * @return Integer ID that represents the position (1-th based) of the task in the TaskList.
     */
    public int getId(String input) throws DukeException {
        try {
            int id = Integer.valueOf(input.split(" ")[1]);
            return id;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid input! Please enter in the correct format with the task index!");
        }

    }

    /**
     * Retrieves the keyword from the user input to for the find task method.
     *
     * @param input User input.
     * @return The keyword used to find the matching tasks in the Task list.
     */
    public String getKeyword(String input) {
        String keyword = input.split(" ", 2)[1];
        return keyword;
    }
}
