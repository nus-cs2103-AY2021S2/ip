/**
 * Represents a Parser which parses user inputs.
 */
public class Parser {

    /**
     * Returns the appropriate command after parsing the input.
     * @param input User input.
     * @return Relevant command.
     * @throws IncompleteCommandException If command is incomplete.
     * @throws NoSuchCommandException If command does not exist.
     */
    static Command parse(String input) throws IncompleteCommandException, NoSuchCommandException {
        Command command;
        if (input.equals("bye")) {
            command = new ExitCommand();
        } else if (input.equals("list")) {
            command = new ListCommand();
        } else if (input.startsWith("done")) {
            command = new DoneCommand(Integer.parseInt(input.substring(5)));
        } else if (input.startsWith("delete")) {
            command = new DeleteCommand(Integer.parseInt(input.substring(7)));
        } else if (input.startsWith("find")) {
            try {
                command = new FindCommand(input.substring(5));
            } catch (Exception e) {
                throw new IncompleteCommandException();
            }
        } else if (input.startsWith("todo")) {
            try {
                command = new ToDoCommand(input.substring(5));
            } catch (Exception e) {
                throw new IncompleteCommandException();
            }
        } else if (input.startsWith("event")) {
            try {
                int index = input.indexOf("/at");
                command = new EventCommand(input.substring(6, index - 1), input.substring(index + 4));
            } catch (Exception e) {
                throw new IncompleteCommandException();
            }
        } else if (input.startsWith("deadline")) {
            try {
                int index = input.indexOf("/by");
                command = new DeadlineCommand(input.substring(9, index - 1), input.substring(index + 4));
            } catch (Exception e) {
                throw new IncompleteCommandException();
            }
        } else {
            throw new NoSuchCommandException();
        }
        return command;
    }
}
