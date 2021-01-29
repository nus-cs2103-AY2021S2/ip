package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    private static final String TODO = "todo";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DUE = "due";
    private static final String FIND = "find";

    public static ArrayList<String> parseToStart(ArrayList<String> oldData) {
        ArrayList<String> parsedData = new ArrayList<>();
        for (String s : oldData) {
            String[] array = s.split(" \\| ");
            if (s.contains("T")) {
                array[0] = "todo";
            } else if (s.contains("D")) {
                array[0] = "deadline";
            } else {
                array[0] = "event";
            }
            String parsedString = array[0];
            for (int i = 1; i < array.length; i++) {
                if (i == 3) {
                    parsedString = array[0].equals("deadline") ? parsedString + " /by " + array[i] : parsedString
                            + " /at " + array[i];
                } else {
                    parsedString = parsedString + " " + array[i];
                }
            }
            parsedData.add(parsedString);
        }
        return parsedData;
    }

    public static Command parse(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("No command typed! Please key in a valid command.");
        } else {
            String[] inputArray = input.split(" ", 2);
            String command = inputArray[0];
            if (command.equals(DELETE) || command.equals(DONE) || command.equals(DUE) || command.equals(FIND)) {
                if (inputArray.length == 1) {
                    throw new DukeException("Missing argument! Please try again.");
                } else if (inputArray.length > 2) {
                    throw new DukeException("Additional arguments! Please try again.");
                } else {
                    try {
                        //Check if the second argument is a valid integer
                        if (command.equals(DONE) || command.equals(DELETE)) {
                            Integer.parseInt(inputArray[1]);
                            return command.equals(DELETE) ? new DeleteCommand(inputArray) : new DoneCommand(inputArray);
                        } else if (command.equals(DUE)) {
                            //Check if the second argument is a valid date
                            LocalDate.parse(inputArray[1]);
                            return new DueCommand(inputArray);
                        } else {
                            return new FindCommand(inputArray);
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Invalid command! Please key in a task number as the second argument.");
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date format! Please key in date in the form yyyy-mm-dd.");
                    }
                }
            } else if (command.equals(LIST)) {
                if (inputArray.length == 1) {
                    return new ListCommand(inputArray);
                } else {
                    throw new DukeException("Additional arguments! Please key in only the command.");
                }
            } else if (command.equals(EVENT) || command.equals(DEADLINE) || command.equals(TODO)) {
                if (inputArray.length == 1) {
                    throw new DukeException("Incomplete command! Please key in the task description.");
                } else {
                    String description = inputArray[1];
                    if (command.equals(TODO)) {
                        return new AddCommand(inputArray);
                    } else if ((command.equals(EVENT) && description.contains("/at"))
                            || (command.equals(DEADLINE) && description.contains("by"))) {
                        String[] arr = command.equals(EVENT) ? description.split("/at", 2)
                                : description.split("/by", 2);
                        if (arr.length == 2) {
                            return new AddCommand(inputArray);
                        } else {
                            throw new DukeException("Invalid command! Please try again.");
                        }
                    } else {
                        throw new DukeException("Invalid command! Please try again.");
                    }
                }
            } else if (command.equals("bye")) {
                return new ByeCommand(inputArray);
            } else {
                throw new DukeException("Invalid command! Please try again.");
            }
        }
    }
}
