import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";

    /**
     * Reads user input and parses it
     *
     * @return Command type object with parameters entered by user
     */
    public static Command parseInput(Scanner scanner) throws MikeInvalidInputException {
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
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new MikeInvalidInputException("☹ OOPS!!! The task to be completed cannot be empty and must " +
                            "be a number.");
                }

            case TODO_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)todo (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new TodoCommand(m.group(1));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            case EVENT_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)event (.+) /at (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new EventCommand(m.group(1), m.group(2));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException("Please enter an event description and timing to add to the " +
                        "list!!");
                }

            case DEADLINE_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)deadline (.+) /by (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new DeadlineCommand(m.group(1), m.group(2));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException("Please enter an event description and deadline to add to " +
                            "the list!!");
                }

            default:
                throw new MikeInvalidInputException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

