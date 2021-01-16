public class Command {
    public static CommandType getType(String str) {
        int strLength = str.length();
        if (str.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (str.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (strLength >= 9 && str.substring(0, 8).equalsIgnoreCase("deadline")
                && str.charAt(8) == ' ') {
            return CommandType.DEADLINE;
        } else if (strLength >= 6 && str.substring(0, 5).equalsIgnoreCase("event")
                && str.charAt(5) == ' ') {
            return CommandType.EVENT;
        } else if (strLength >= 6 && str.substring(0, 4).equalsIgnoreCase("done")
                && str.charAt(4) == ' ') {
            return CommandType.DONE;
        } else if (strLength >= 5 && str.substring(0, 4).equalsIgnoreCase("todo")
                && str.charAt(4) == ' ') {
            return CommandType.TODO;
        } else {
            return CommandType.OTHER;
        }
    }

    public static Task convertToTask(String command, CommandType type) throws DukeException {
        switch (type) {
            case TODO:
                return new Todo(command.substring(5));
            case DEADLINE:
                int indexOfBy = command.toLowerCase().indexOf("/by");
                if (indexOfBy > 0 && command.charAt(indexOfBy - 1) == ' '
                        && command.charAt(indexOfBy + 3) == ' ') {
                    return new Deadline(command.substring(9, indexOfBy - 1), command.substring(indexOfBy + 4));
                } else {
                    throw new DukeException("Invalid Argument");
                }
            case EVENT:
                int indexOfAt = command.toLowerCase().indexOf("/at");
                if (indexOfAt > 0 && command.charAt(indexOfAt - 1) == ' '
                        && command.charAt(indexOfAt + 3) == ' ') {
                    return new Event(command.substring(6, indexOfAt - 1), command.substring(indexOfAt + 4));
                } else {
                    throw new DukeException("Invalid Argument");
                }
            default:
                throw new DukeException("Invalid Type");

        }
    }


}
