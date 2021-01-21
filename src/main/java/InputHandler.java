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
    private static final String DELETE_COMMAND = "delete";

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
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Done command format. eg.\n" +
                            "   Done <index of task to mark completed>");
                }

            case TODO_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)todo (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new TodoCommand(m.group(1));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Todo command format. eg.\n" +
                            "   todo <description>");
                }

            case EVENT_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)event (.+) /at (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new EventCommand(m.group(1), m.group(2));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Event command format. eg.\n" +
                            "   event <description> /at <deadline>");
                }

            case DEADLINE_COMMAND:
                try {
                    Pattern p = Pattern.compile("(?i)deadline (.+) /by (.+)");
                    Matcher m = p.matcher(userInput);
                    m.find();
                    return new DeadlineCommand(m.group(1), m.group(2));
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Deadline command format. eg.\n" +
                            "   deadline <description> /by <deadline>");
                }

            case DELETE_COMMAND:
                try {
                    return new DeleteCommand(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Delete command format. eg.\n" +
                            "   Delete <index of task to delete>");
                }

            default:
                throw new MikeInvalidInputException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

