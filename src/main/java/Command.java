public class Command {
    public static CommandType getType(String str) throws DukeException {
        int strLength = str.length();
        if (str.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (str.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (str.length() >= 8 && str.substring(0, 8).equalsIgnoreCase("deadline")) {
            if (str.length() == 8 || (str.length() <= 9 && str.charAt(8) != ' ')) {
                throw new DukeException("No argument");
            } else if (str.charAt(8) != ' ') {
                return CommandType.OTHER;
            } else {
                int indexOfBy = str.toLowerCase().indexOf("/by");
                if (indexOfBy > 9 && str.charAt(indexOfBy - 1) == ' '
                        && str.charAt(indexOfBy + 3) == ' ') {
                    return CommandType.DEADLINE;
                } else {
                    throw new DukeException("Invalid argument");
                }
            }
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("event")) {
            if (str.length() == 5 || (str.length() <= 6 && str.charAt(5) == ' ')) {
                throw new DukeException("No argument");
            } else if (str.charAt(5) != ' ') {
                return CommandType.OTHER;
            } else {
                int indexOfAt = str.toLowerCase().indexOf("/at");
                if (indexOfAt > 6 && str.charAt(indexOfAt - 1) == ' '
                        && str.charAt(indexOfAt + 3) == ' ') {
                    return CommandType.EVENT;
                } else {
                    throw new DukeException("Invalid argument");
                }
            }
        } else if (strLength >= 4 && str.substring(0, 4).equalsIgnoreCase("todo")) {
            if (str.length() == 4 || (str.length() <= 5 && str.charAt(4) == ' ')) {
                throw new DukeException("No argument");
            } else if (str.charAt(4) != ' ') {
                return CommandType.OTHER;
            } else {
                return CommandType.TODO;
            }
        } else if (strLength >= 4 && str.substring(0, 4).equalsIgnoreCase("done")) {
            if (str.length() == 4 || (str.length() <= 5 && str.charAt(4) == ' ')) {
                throw new DukeException("No argument");
            } else if (str.charAt(4) != ' ') {
                return CommandType.OTHER;
            } else {
                try {
                    Integer.parseInt(str.substring(5));
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid argument");
                }
                return CommandType.DONE;
            }
        } else {
            return CommandType.OTHER;
        }
    }

    public static Task convertToTask(String command, CommandType type) throws DukeException {
        switch (type) {
            case TODO -> {
                String subStr = command.substring(5);
                if (StringParser.isBlank(subStr)) {
                    throw new DukeException("Void argument");
                } else {
                    return new Todo(command.substring(5));
                }
            }
            case DEADLINE -> {
                int indexOfBy = command.toLowerCase().indexOf("/by");
                String subStrContent = command.substring(9, indexOfBy - 1);
                String subStrTime = command.substring(indexOfBy + 4);
                if (StringParser.isBlank(subStrContent)) {
                    throw new DukeException("Void argument");
                } else if (StringParser.isBlank(subStrTime)) {
                    throw new DukeException("Void argument");
                } else {
                    return new Deadline(subStrContent, subStrTime);
                }
            }
            case EVENT -> {
                int indexOfAt = command.toLowerCase().indexOf("/at");
                String subStrContent = command.substring(6, indexOfAt - 1);
                String subStrTime = command.substring(indexOfAt + 4);
                if (StringParser.isBlank(subStrContent)) {
                    throw new DukeException("Void argument");
                } else if (StringParser.isBlank(subStrTime)) {
                    throw new DukeException("Void argument");
                } else {
                    return new Event(subStrContent, subStrTime);
                }
            }
            default -> {
                throw new DukeException("Invalid command");
            }
        }
    }


}
