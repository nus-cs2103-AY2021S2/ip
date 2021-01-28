public class Parser {
    //public String commandName;

    public static Command parse(String fullCommand) throws DukeException {
        return new Command(fullCommand);
    }
}
