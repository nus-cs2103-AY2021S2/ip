package duke.parser;

import duke.exception.DukeException;

/**
 * A parser for reading user input
 */
public class Parser {
    private String msg;

    /**
     * Constructor for <code>Parser</code>.
     *
     * @param msg a string of input from user
     */
    public Parser(String msg) {
        this.msg = msg;
    }

    /**
     * Process msg and return the request (command) of the input.
     *
     * @return a string of request
     */
    public String getRequest() {
        String[] token = msg.split(" ");
        return token[0];
    }

    /**
     * Process msg and return the arguments of the input.
     *
     * @return a string of arguments
     */
    public String getArgs() {
        String[] token = msg.split(" ");
        String args = "";
        for (int i = 1; i < token.length; i++) {
            args += token[i];
            if (i < token.length - 1) {
                args += " ";
            }
        }
        return args;
    }

    /**
     * Process msg and return the formatted command of the input.
     * This method return an String array of size 3.
     * The first item represents the name of a task.
     * The second item represents the date of a task.
     * The third item represents the preposition of a task.
     *
     * @return an array consisting of name, date, preposition
     * @throws DukeException
     */
    public String[] getFormattedCommand() throws DukeException {
        try {
            String args = getArgs();
            String[] formattedArr = new String[3];
            formattedArr[0] = args.split(" /")[0];
            formattedArr[1] = args.split("/")[1].substring(args.split("/")[1]
                    .split(" ")[0].length() + 1, args.split("/")[1].length());
            formattedArr[2] = args.split("/")[1].split(" ")[0];
            return formattedArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }
}
