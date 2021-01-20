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
        case "todo":
        case "event":
        case "deadline":
            String description = processDescription(processedInput);
            return new AddCommand(command, description);
        default:
            System.out.println("Invalid command. Please enter a valid one");
            return new ByeCommand();
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
