public class Parser {
    /**
     * Parses the user input to find and handle commands containing keywords.
     * @param userInput
     * @throws DukeException if command is in an incorrect format.
     */
    public static Command handleInput(String userInput) throws DukeException {
        String[] splitBySpaces = userInput.trim().split("\\s+");
        String keyword = splitBySpaces[0];
        if (keyword.equals("list")) {
            return new ListCommand(splitBySpaces);
        } else if (keyword.equals("done")) {
            return new DoneCommand(splitBySpaces);
        } else if (keyword.equals("deadline") || keyword.equals("todo") || keyword.equals("event")) {
            return new AddCommand(splitBySpaces);
        } else if (keyword.equals("delete")) {
            return new DeleteCommand(splitBySpaces);
        } else if (keyword.equals("bye")) {
            return new ByeCommand(splitBySpaces);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
