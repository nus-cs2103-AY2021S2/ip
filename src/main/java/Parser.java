public class Parser {
    private final static String TODO = "todo";
    private final static String DELETE = "delete";
    private final static String DONE = "done";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";
    private final static String LIST = "list";

    public static String[] check(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("No command typed! Please key in a valid command.");
        } else {
            String[] inputArray = input.split(" ", 2);
            String command = inputArray[0];
            if (command.equals(DELETE) || command.equals(DONE)) {
                if (inputArray.length == 1) {
                    throw new DukeException("Missing argument! Please key in the command followed by the task number.");
                } else if (inputArray.length > 2) {
                    throw new DukeException("Additional arguments! Please key in the command followed by the task number.");
                } else {
                    try {
                        //Check if the second argument is a valid integer
                        Integer.parseInt(inputArray[1]);
                        return inputArray;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Invalid command! Please key in a task number as the second argument.");
                    }
                }
            } else if (command.equals(LIST)) {
                if (inputArray.length == 1) {
                    return inputArray;
                } else {
                    throw new DukeException("Additional arguments! Please key in only the command.");
                }
            } else if (command.equals(EVENT) || command.equals(DEADLINE) || command.equals(TODO)) {
                if (inputArray.length == 1) {
                    throw new DukeException("Incomplete command! Please key in the task description.");
                } else {
                    String description = inputArray[1];
                    if (command.equals(TODO)) {
                        return inputArray;
                    } else if ((command.equals(EVENT) && description.contains("/at")) ||
                            (command.equals(DEADLINE) && description.contains("by"))) {
                        String[] arr = command.equals(EVENT) ? description.split("/at", 2) :
                                description.split("/by", 2);
                        if (arr.length == 2) {
                            return inputArray;
                        } else {
                            throw new DukeException("Invalid command! Please try again.");
                        }
                    } else {
                        throw new DukeException("Invalid command! Please try again.");
                    }
                }
            } else if (command.equals("bye")) {
                return inputArray;
            } else {
                throw new DukeException("Invalid command! Please try again.");
            }
        }
    }
}
