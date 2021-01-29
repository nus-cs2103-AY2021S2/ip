public class Parser {


    /**
     * returns the Command to be carried out based on the input string.
     *
     * @param command String containing user input
     * @return Command object based on the instruction of the String
     * @throws DukeException if the string is not a valid instruction
     */
    public static Command parse(String command) throws DukeException {
        String[] info = command.split(" ");
        if (info[0].equals("todo") || info[0].equals("event") || info[0].equals("deadline")) {
            return new AddCommand(info);
        } else if (info[0].equals("done")) {
            return new DoneCommand(info);
        } else if (info[0].equals("delete")) {
            return new DeleteCommand(info);
        } else if (info[0].equals("list")) {
            return new PrintCommand(info);
        } else if (info[0].equals("bye")) {
            return new ByeCommand();
        } else if (info[0].equals("find")) {
            return new FindCommand(info);
        } else {
            throw new DukeException("Sorry but I don't understand what that means! :-(");
        }
    }
}
