public class Parser {

    public static Command parse(String userInput) throws DukeException {
        String[] inputs = userInput.split(" ");
        if (inputs.length == 0) {
            throw new DukeException("OOPS! Please enter a command or say bye so I can go back to sleep!");
        }
        String action = inputs[0];
        if (action.equals("list")) {
            return new ListCommand(userInput, "list");
        } else if (action.equals("done")) {
            return new DoneCommand(userInput, "done");
        } else if (action.equals("todo")) {
            return new AddCommand(userInput, "todo");
        } else if (action.equals("deadline")) {
            return new AddCommand(userInput, "deadline");
        } else if (action.equals("event")) {
            return new AddCommand(userInput, "event");
        } else if (action.equals("delete")) {
            return new DeleteCommand(userInput, "delete");
        } else if (action.equals("date")) {
            return new DateCommand(userInput, "date");
        } else {
            throw new DukeException("OOPS! Sorry, I have no idea what that means :(");
        }
    }
}
