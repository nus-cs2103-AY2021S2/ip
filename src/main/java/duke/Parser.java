package duke;

/** Processes user input and possible input-based errors
 * and sorts to relevant commandList methods or displays
 * correct error messages to prompt user for correct inputs.
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    CommandList commandList;
    static String terminate = "bye";
    private final static String separator = " ";
    private final static String regexToDo = " ";
    private final static String regexDeadline = " /by ";
    private final static String regexEvent = " /at ";

    public Parser(CommandList commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles and displays specified error
     * messages as per different cases of
     * incorrect user input
     *
     * @param errorInput Raw user input
     * @throws DukeException if description is empty or in wrong formats
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

            result = (Ui.spacer
                    + "Eh? Your command description cannot be empty."
                    + "Try again!"
                    + Ui.spacer);
//            throw new DukeException(" Error: Empty Command");

        } else if (errorInput.contains("list")
                || errorInput.contains("bye")) {
            //Do nothing

        } else if ((errorInput.contains("done")
                || errorInput.contains("delete"))
                && inputArr[1] == null) {

            result = (Ui.spacer
                    + "What are you referring to?"
                    + "Remember to key in the correct command id!"
                    + Ui.spacer);
//            throw new DukeException("Error: Non-existent id");

        } else if (inputArr[1] != null) {
            //Do nothing

        // Invalid description: unknown
        } else {
            result = (Ui. spacer
                    + "Whoops :( I'm sorry, I'm not sure what that means. "
                    + "Did you forget to add a command type?"
                    + Ui.spacer) ;
//            throw new DukeException("Error: Non-existent command type");
        }

        return result;
    }

    /**
     * Sorts user input accordingly and splits input by
     * command type to relay formatted input to addCommand
     *
     * @see duke.CommandList#addCommand(Command, String) addCommand
     * @param input Raw user input to add commands
     * @exception DateTimeParseException if the LocalDate user input is in wrong format
     */
    String parseAdd(String input) {
        Command command;
        String result = "";
        try {
            if (input.contains("todo")) {
                String[] description = input.split(regexToDo);
                command = new ToDo(description[1]);
                result = commandList.addCommand(command, "T");

            } else if (input.contains("deadline")) {
                String[] inputTime = input.split(regexDeadline);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Deadline(inputTime[0].substring(9), parseDate);
                result =  commandList.addCommand(command, "D");

            } else if (input.contains("event")) {
                String[] inputTime = input.split(regexEvent);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Event(inputTime[0].substring(6), parseDate);
                result =  commandList.addCommand(command, "E");

            } else {
                result = "An error occurred, did you add the correct command?";
            }
        } catch (DateTimeParseException e) {
            result = (Ui.spacer
                    + "This date doesnt exist! "
                    + "The right format should be in yyyy-mm-dd."
                    + Ui.spacer);
            return result;
        }
        return result;
    }

     String parseList() {
       return (Ui.spacer
               + commandList.printList()
               + Ui.spacer);
   }

    /**
     * Splits input to get id of desired done command
     * to relay formatted input to doneCommand
     *
     * @see duke.CommandList#doneCommand(int) doneCommand
     * @param input Raw user input to mark command
     */
    String parseDone(String input) {
       String[] doneInput = input.split(separator);
       int id = Integer.parseInt(doneInput[1]) - 1;
       return commandList.doneCommand(id);
   }

    /**
     * Splits input to get id of desired deleted command
     * to relay formatted input to deleteCommand
     *
     * @see duke.CommandList#deleteCommand(int) deleteCommand
     * @param input Raw user input to delete command
     */
    String parseDelete(String input) {
        String[] deleteInput = input.split(separator);
        int id = Integer.parseInt(deleteInput[1]) - 1;
        return commandList.deleteCommand(id);
    }

    /**
     * Splits input to get keyWord from user
     * to relay formatted input to findCommand
     *
     * @see duke.CommandList#findCommand(String) findCommand
     * @param input Raw user input to mark command
     */
    String parseFind(String input) {
        String[] findInput = input.split(separator);
        return commandList.findCommand(findInput[1]);
    }
    
    /**
     * Introduces scanner class to read in input
     * and sorts input into appropriate parsing commands
     *
     */
    public String parseAll(String input) {
        String result = "";

        try {
            result = errorHandling(input);

            if (input.equals(terminate)) {
                result = Ui.printGoodbye();
            } else if (input.equals("list")) {
                result = parseList();
            } else if (input.contains("hi")
                    || input.contains("hello")
                    || input.equals("hi")
                    || input.equals("hello")) {
                result = Ui.printGreet();
            } else if (input.contains("done")) {
                result = parseDone(input);
            } else if (input.contains("delete")) {
                result = parseDelete(input);
            } else if(input.contains("find")) {
                result = parseFind(input);
            } else if (input.contains("todo")
                    || input.contains("deadline")
                    || input.contains("event")) {
                result = parseAdd(input);
            } else {
                result = Ui.printGeneralError();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
