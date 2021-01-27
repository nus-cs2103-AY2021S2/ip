package duke.parser;

import duke.exception.DukeException;

public class Parser {
    private String msg;

    public Parser(String msg) {
        this.msg = msg;
    }

    public String getRequest() {
        String[] tk = msg.split(" ");
        return tk[0];
    }

    public String getArgs() {
        String[] tk = msg.split(" ");
        String args = "";
        for (int i = 1; i < tk.length; i ++) {
            args += tk[i];
            if (i < tk.length - 1) {
                args += " ";
            }
        }
        return args;
    }

    public String[] getFormattedCommand() throws DukeException {
        try {
            String args = getArgs();
            String[] arr = new String[3];
            arr[0] = args.split(" /")[0];
            arr[1] = args.split("/")[1].substring(args.split("/")[1].split(" ")[0].length() + 1, args.split("/")[1].length());
            arr[2] = args.split("/")[1].split(" ")[0];
            return arr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }
}
