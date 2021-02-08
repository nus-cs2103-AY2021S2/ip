public class Parser {


    /**
     * returns the Command to be carried out based on the input string.
     *
     * @param command String containing user input
     * @return Command object based on the instruction of the String
     * @throws DukeException if the string is not a valid instruction
     */
    public static Command parse(String command) throws DukeException {
        String[] info = command.split(" ");
        if (info[0].equals("todo") || info[0].equals("event") || info[0].equals("deadline")) {
            checkAddCommand(info);
            return new AddCommand(info);
        } else if (info[0].equals("done")) {
            checkDoneCommand(info);
            return new DoneCommand(info);
        } else if (info[0].equals("delete")) {
            checkDeleteCommand(info);
            return new DeleteCommand(info);
        } else if (info[0].equals("list")) {
            checkPrintCommand(info);
            return new PrintCommand(info);
        } else if (info[0].equals("bye")) {
            checkByeCommand(info);
            return new ByeCommand();
        } else if (info[0].equals("find")) {
            checkFindCommand(info);
            return new FindCommand(info);
        } else if (info[0].equals("mark")) {
            checkPriorityCommand(info);
            return new PriorityCommand(info);
        } else {
            throw new DukeException("Sorry but I don't understand what that means! :(");
        }
    }

    private static void checkPriorityCommand(String[] info) throws DukeException {
        if (info.length <= 2) {
            throw new DukeException("Don't forget to provide the task number and priority level");
        } else if (info.length > 3) {
            throw new DukeException("OOPS! You provided extra information I don't need.");
        }
        try {
            int taskNumber = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number :(");
        }
    }

    private static void checkFindCommand(String[] info) throws DukeException {
        if (info.length == 1) {
            throw new DukeException("OOPS! No keyword is given!");
        }
    }

    private static void checkByeCommand(String[] info) throws DukeException {
        if (info.length > 1) {
            throw new DukeException("Please remove the additional information provided!");
        }
    }

    private static void checkPrintCommand(String[] info) throws DukeException {
        if (info.length > 1) {
            throw new DukeException("Please remove the additional information provided!");
        }
    }

    private static void checkDeleteCommand(String[] info) throws DukeException {
        int length = info.length;
        if (length == 1) {
            throw new DukeException("Please provide the number of the task to delete");
        }
        try {
            int taskNumber = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid command :(");
        }
    }

    private static void checkDoneCommand(String[] info) throws DukeException {
        int length = info.length;
        if (length == 1) {
            throw new DukeException("OOPS! Task completed is not specified");
        }
        try {
            int taskNumber = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid command :(");
        }
    }

    private static void checkAddCommand(String[] info) throws DukeException {
        int length = info.length;
        if (info[0].equals("todo") && length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        } else if (info[0].equals("event") && length == 1) {
            throw new DukeException("OOPS! Specifics are needed for this event");
        } else if (info[0].equals("deadline") && length == 1) {
            throw new DukeException("OOPS! Specifics are needed for this deadline");
        }
    }
}
