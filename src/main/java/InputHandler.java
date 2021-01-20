public class InputHandler {

    public static Command parse(String input) {

        switch (input) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        default:
            return new AddCommand(input);
        }
        
    }
}
