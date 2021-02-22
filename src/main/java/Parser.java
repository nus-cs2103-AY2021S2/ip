/**
 * A class that deals with making sense of the user command.
 */

public class Parser {

    /**
     * Parses the user input in the form of a <code>String</code>
     * to generate the corresponding <code>Command</code>
     * @param userInput the user input
     * @return a specific type of command
     * @throws DukeException if the user did not enter a command, or if the user entered
     * an unknown command
     */
    public static Command parse(String userInput) throws DukeException {
        String[] inputs = userInput.split(" ");
        if (inputs.length == 0) {
            throw new DukeException("Ooh lah lah! Please enter a command or say bye so I can go back to sleep!");
        }
        String typeOfCommand = inputs[0];
        if (typeOfCommand.equals("list")) {
            return new ListCommand(userInput, "list");
        } else if (typeOfCommand.equals("done")) {
            return new DoneCommand(userInput, "done");
        } else if (typeOfCommand.equals("todo")) {
            return new AddCommand(userInput, "todo");
        } else if (typeOfCommand.equals("deadline")) {
            return new AddCommand(userInput, "deadline");
        } else if (typeOfCommand.equals("event")) {
            return new AddCommand(userInput, "event");
        } else if (typeOfCommand.equals("delete")) {
            return new DeleteCommand(userInput, "delete");
        } else if (typeOfCommand.equals("date")) {
            return new DateCommand(userInput, "date");
        } else if (typeOfCommand.equals("find")) {
            return new FindCommand(userInput, "find");
        } else if (typeOfCommand.equals("reschedule")) {
            return new RescheduleCommand(userInput, "reschedule");
        } else {
            throw new DukeException("Ooh lah lah! Pardon, I have no idea what that means :(");
        }
    }
}
