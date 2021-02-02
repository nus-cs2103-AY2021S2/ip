package duke.parser;

import duke.exception.DukeException;

public class Parser {
    private String msg;

    public Parser(String msg) {
        this.msg = msg;
    }

    public String getRequest() {
        String[] token = msg.split(" ");
        return token[0];
    }

    public String getArgs() {
        String[] token = msg.split(" ");
        String args = "";
        for (int i = 1; i < token.length; i ++) {
            args += token[i];
            if (i < token.length - 1) {
                args += " ";
            }
        }
        return args;
    }

    public String[] getFormattedCommand() throws DukeException {
        try {
            String args = getArgs();
            String[] formattedArr = new String[3];
            formattedArr[0] = args.split(" /")[0];
            formattedArr[1] = args.split("/")[1].substring(args.split("/")[1].split(" ")[0].length() + 1, args.split("/")[1].length());
            formattedArr[2] = args.split("/")[1].split(" ")[0];
            return formattedArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }
}
