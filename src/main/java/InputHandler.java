import java.util.Scanner;

public class InputHandler {

    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";

    /**
     * Reads user input and parses it
     * @return Command type object with parameters entered by user
     */
    public static Command parseInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String[] userInputArr = userInput.split(" ");

        switch (userInputArr[0].toLowerCase()) {
            case BYE_COMMAND:
                return new ByeCommand();

            case LIST_COMMAND:
                return new ListCommand();

            case DONE_COMMAND:
                try {
                    return new DoneCommand(Integer.parseInt(userInputArr[1]));
                } catch(NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
                    return new ExceptionCommand("Please enter a task index to mark as completed!!");
                }

            default:
                return new AddCommand(userInput);
        }
    }
}

