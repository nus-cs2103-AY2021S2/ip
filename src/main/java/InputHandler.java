import java.util.Scanner;

public class InputHandler {

    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";

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

            case TODO_COMMAND:
                try {
                    return new TodoCommand(userInputArr[1]);
                } catch(NullPointerException | IndexOutOfBoundsException e) {
                    return new ExceptionCommand("Please enter a task description to add to the list!!");
                }

                case EVENT_COMMAND:
                try {
                    if (userInputArr[2].matches("/at(.*)")) {
                        return new EventCommand(userInputArr[1], userInputArr[2]);
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                } catch(NullPointerException | IndexOutOfBoundsException e) {
                    return new ExceptionCommand("Please enter an event description and timing to add to the list!!");
                }
            default:
                return new TodoCommand(userInput);
        }
    }
}

