/**
 * Parses user input.
 */
public class Parser {

    /** String array of user input */
    private String[] input;
    private final String DELETE_MESSAGE = "delete";

    /**
     * Creates Parser object from given String input.
     *
     * @param input String input to be parsed.
     */
    public Parser(String input) {
        this.input = input.split(" ");
    }

    /**
     * Returns a new Parser object with the new given String input.
     *
     * @param input New String input to be parsed.
     * @return New Parser object with given String input.
     */
    public Parser newInput(String input) {
        return new Parser(input);
    }

    /**
     * Returns first word or letter from user input.
     *
     * @return The first word or letter from user input.
     */
    public String getCommand() {
        return input[0];
    }

    /**
     * Returns number, given by user, indicating index of task on task list to be modified.
     *
     * @return Integer stating the index of the task on task list to be modified.
     * @throws DukeException If no input is found.
     */
    public int getIndexToModify() throws DukeException {

        String index = "";

        try {
            index = input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DELETE_MESSAGE);
        }

        return Integer.parseInt(index) - 1;
    }

    /**
     * Returns keyword given by user to search tasks for.
     *
     * @return String of the keyword.
     * @throws DukeException If no keyword is found.
     */
    public String getKeyword() throws DukeException {

        try {
            return input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("search");
        }
    }

    /**
     * Returns description of the Task that user wants to add.
     *
     * @return String comprising description of the task.
     */
    public String getTaskDescription() {
        String output = "";

        for (int counter = 1; counter < input.length; counter++) {
            if (input[counter].startsWith("/")) {
                break;
            } else {
                output = output + " " + input[counter];
            }
        }

        return output;
    }

    /**
     * Returns String comprising date or deadline of the task.
     *
     * @return String comprising date or deadline of the task.
     */
    public String getDate() {
        if (getCommand().equals("todo")) {
            return "Task has no date";
        } else {
            return input[input.length - 2];
        }
    }

    /**
     * Returns String comprising time of the task.
     *
     * @return String comprising time of the task.
     */
    public String getTime() {
        if (getCommand().equals("todo")) {
            return "Task has no time";
        } else {
            return input[input.length - 1];
        }
    }
}
