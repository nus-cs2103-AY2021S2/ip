public class Parser {

    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.contains("done")) {
            return new DoneCommand(command);
        } else if (command.contains("todo")) {
            return new ToDoCommand(command);
        } else if (command.contains("deadline")) {
            return new DeadlineCommand(command);
        } else if (command.contains("event")) {
            return new EventCommand(command);
        } else if (command.contains("delete")) {
            return new DeleteCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
