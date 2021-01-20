public class InputHandler {

    public static Command parse(String input) throws DukeException {

        String[] processedInput = input.split(" ");
        String command = processedInput[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "done":
            if (processedInput.length == 1) {
                throw new DukeException("Please enter a number to mark done");
            }
            int id = Integer.parseInt(processedInput[1]);
            return new DoneCommand(id);
        case "todo":
        case "event":
        case "deadline":
            if (processedInput.length == 1) {
                throw new DukeException("Descriptions cannot be empty, you need to type something.");
            }
            String description = processDescription(processedInput);
            return new AddCommand(command, description);
        default:
            throw new DukeException("Invalid command. Please enter a valid one");
        }
        
    }

    public static String processDescription(String[] processedInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < processedInput.length; i++) {
            sb.append(processedInput[i] + " ");
        }
        return sb.toString();
    }
}
