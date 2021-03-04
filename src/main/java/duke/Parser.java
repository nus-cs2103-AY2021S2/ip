package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Processes user input and possible input-based errors
 * and sorts to relevant commandList methods or displays
 * correct error messages to prompt user for correct inputs.
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */
public class Parser {
    private CommandList commandList;
    private final String regexSpace = " ";
    private final String regexToDo = " ";
    private final String regexDeadline = " /by ";
    private final String regexEvent = " /at ";
    private final String terminate = "bye";

    public Parser(CommandList commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles and displays specified error
     * messages as per different cases of
     * incorrect user input
     *
     * @param errorInput Raw user input
     * @return Error Message to prompt correct input from user
     */
    static String errorHandling(String errorInput) {
        String[] inputArr = new String[100];
        String result = "";

        if (errorInput.contains(" ")) {
            inputArr = errorInput.split(" ");
        } else {
            inputArr[0] = errorInput;
        }

        if ((errorInput.contains("todo")
                || errorInput.contains("deadline")
                || errorInput.contains("event"))
                && inputArr[1] == null) {

            result = ("Eh? Your command description cannot be empty."
                    + "Try again!");

        } else if (errorInput.contains("list")
                || errorInput.contains("bye")) {
            //Do nothing

        } else if ((errorInput.contains("done")
                || errorInput.contains("delete"))
                && inputArr[1] == null) {

            result = ("What are you referring to?"
                    + " Remember to key in the correct command id!");

        } else if (inputArr[1] != null) {
            //Do nothing

        // Invalid description: unknown
        } else {
            result = ("Whoops :( I'm sorry, I'm not sure what that means. "
                    + "Did you forget to add a command type?");
        }

        return result;
    }

    /**
     * Sorts user input accordingly and splits input by
     * command type to relay formatted input to addCommand
     *
     * @param input Raw user input to add commands
     * @return Added notification to user
     * @exception DateTimeParseException if the LocalDate user input is in wrong format
     * @see duke.CommandList#addCommand(Command, String) addCommand
     */
    String parseAdd(String input) {
        Command command;
        String result = "";
        try {
            if (input.contains("todo")) {
                String[] description = input.split(regexToDo);
                command = new ToDo(input.substring(5));
                result = commandList.addCommand(command, "T");

            } else if (input.contains("deadline")) {
                String[] inputTime = input.split(regexDeadline);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Deadline(inputTime[0].substring(9), parseDate);
                result = commandList.addCommand(command, "D");

            } else if (input.contains("event")) {
                String[] inputTime = input.split(regexDeadline);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Event(inputTime[0].substring(6), parseDate);
                result = commandList.addCommand(command, "E");

            } else {
                result = "An error occurred, did you add the correct command?";
            }
        } catch (DateTimeParseException e) {
            result = ("This date doesnt exist! "
                    + "The right format should be in yyyy-mm-dd.");
            return result;
        }
        return result;
    }

    String parseList() {
        return commandList.printList();
    }

    /**
     * Splits input to get id of desired done command
     * to relay formatted input to doneCommand
     *
     * @param input Raw user input to mark command
     * @return Done notification to user
     * @see duke.CommandList#doneCommand(int) doneCommand
     */
    String parseDone(String input) {
        String[] doneInput = input.split(regexSpace);
        int id = Integer.parseInt(doneInput[1]) - 1;
        return commandList.doneCommand(id);
    }

    /**
     * Splits input to get id of desired deleted command
     * to relay formatted input to deleteCommand
     *
     * @param input Raw user input to delete command
     * @return Delete notification to user
     * @see duke.CommandList#deleteCommand(int) deleteCommand
     */
    String parseDelete(String input) {
        String[] deleteInput = input.split(regexSpace);
        int id = Integer.parseInt(deleteInput[1]) - 1;
        return commandList.deleteCommand(id);
    }

    /**
     * Splits input to get keyWord from user
     * to relay formatted input to findCommand
     *
     * @param input Raw user input to mark command
     * @return Found or Missing keyWord notification to user
     * @see duke.CommandList#findCommand(String) findCommand
     */
    String parseFind(String input) {
        String[] findInput = input.split(regexSpace);
        return commandList.findCommand(findInput[1]);
    }

    String parseArchive(String input) {
        String result = "";
        if (input.contains("retrieve")) {
            result = commandList.retrieveCommand();
        } else {
            result = commandList.archiveCommand();
        }
        return result;
    }

    /**
     * Introduces scanner class to read in input
     * and sorts input into appropriate parsing commands
     *
     * @param input Raw user input from GUI
     * @return Relevant response to user
     */
    public String parseAll(String input) {
        assert input.length() > 0 : "Command cannot be empty";
        String result = "";
        try {
            result = errorHandling(input);

            if (input.equals(terminate)) {
                result = Ui.printGoodbye();
                new SoundBark().playAudio();

            } else if (input.equals("list")) {
                result = parseList();

            } else if (input.contains("hello")
                    || input.equals("hello")) {
                result = Ui.printGreet();
                new SoundBark().playAudio();

            } else if (input.contains("done")) {
                result = parseDone(input);

            } else if (input.contains("delete")) {
                result = parseDelete(input);

            } else if (input.contains("find")) {
                result = parseFind(input);

            } else if (input.contains("todo")
                    || input.contains("deadline")
                    || input.contains("event")) {
                result = parseAdd(input);

            } else if (input.contains("archive")
                    || input.equals("archive")) {
                result = parseArchive(input);

            } else {
                result = Ui.printGeneralError();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
