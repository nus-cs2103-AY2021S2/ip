package justin;

import justin.JustinException;

/**
 * This class creates a Parser class
 * that takes in a String input
 * and generate a command to be executed
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Parser {

    private String fullText;

    /**
     * This method creates a Parser class
     *
     * @param fullText of the user input
     */

    public Parser(String fullText) {
        this.fullText = fullText;
    }

    /**
     * This method checks the user input
     * and generate a command
     * to be executed
     *
     * @return String The command to be executed
     * @throws JustinException command not valid
     */

    public String checkCommand() throws JustinException {
        try {

            validate(fullText);

            if (fullText.equals("bye")) {

                return "BYE";

            } else if (fullText.equals("list")) {

                return "LIST";

            } else if (fullText.contains("done")) {

                return "DONE";

            } else if (fullText.contains("deadline")) {

                return "DEADLINE";

            } else if (fullText.contains("todo")) {

                return "TODO";

            } else if (fullText.contains("event")) {

                return "EVENT";

            }
            else if (fullText.contains("delete")) {

                return "DELETE";

            } else if (fullText.contains("find")) {

                return "FIND";

            }

            else {

                return "ADD";

            }
        } catch (JustinException m) {
            throw new JustinException(m.getMessage());
        }
    }

    static void validate(String text) throws JustinException {
        if (text.length() < 5 && text.contains("todo") ) { // case 1
            throw new JustinException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if (text.contains("blah")) { // case 2
            throw new JustinException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (text.length() < 10 && text.contains("deadline")) { // case 3
            throw new JustinException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.length() < 6 && text.contains("event")) { // case 4
            throw new JustinException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

}