public class InputHandler {

    public static Command parse(String input) {

        String[] processedInput = input.split(" ");
        String command = processedInput[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "done":
            int id = Integer.parseInt(processedInput[1]);
            return new DoneCommand(id);
        default:
            return new AddCommand(input);
        }
        
    }
}
