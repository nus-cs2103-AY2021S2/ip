public class Parser {
    public static Command parseInput(String input) {
        String[] parts = input.split(" ", 2);

        Command command;
        if (parts.length < 2) {
            command = new Command(parts[0]);
        } else {
            command = new Command(parts[0], parts[1]);
        }

        return command;
    }
}
