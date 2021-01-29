package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public LocalDateTime parseDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        return dateTime;
    }

    public String parseCommand(String input) {
        String[] command = input.split(" ");
        return command[0];
    }

    public boolean isCorrectTodo(String input) {
        String[] command = input.split(" ");
        if (command.length == 1) {
            return false;
        } else if (command[1].equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String parseTodoDescripton(String input) {
        String[] command = input.split(" ");
        String description = "";
        for (int i = 1; i < command.length; i++) {
            description += command[i] + " ";
        }
        return description;
    }

    public boolean isCorrectDeadline(String input) {
        String[] command = input.split(" ");
        if (command.length == 1) {
            return false;
        } else if (command[1].equals("/by")) {
            return false;
        } else {
            String[] details = input.split(" /by ");
            if (details.length != 2) {
                return false;
            } else {
                    return true;
            }
        }
    }

    public String parseDeadlineDescription(String input) {
        String[] details = input.split(" /by ");
        return details[0].substring(9);
    }

    public LocalDateTime parseDeadlineDate(String input) {
        String[] details = input.split(" /by ");
        return parseDate(details[1]);
    }

    public boolean isCorrectEvent(String input) {
        String[] command = input.split(" ");
        if (command.length == 1) {
            return false;
        } else if (command[1].equals("/from")) {
            return false;
        } else {
            String[] details = input.split(" /from ");
            if (details.length != 2) {
                return false;
            } else {
                String[] dates = details[1].split(" /to ");
                if (dates.length != 2) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public String parseEventDescription(String input) {
        String[] details = input.split(" /from ");
        return details[0].substring(6);
    }

    public LocalDateTime parseEventStartDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[0]);
    }

    public LocalDateTime parseEventEndDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[1]);
    }

    public boolean isCorrectList(String input) {
        if (input.equals("list") || input.equals("list ")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCorrectIndexCommand(String input, int size) {
        String[] command = input.split(" ");
        if (input.equals("done") || input.equals("done ")) {
            return false;
        } else if (command.length != 2) {
            return false;
        } else {
            int index = Integer.valueOf(command[1]) - 1;
            if (index < 0 || index >= size) {
                return false;
            } else {
                return true;
            }
        }
    }

    public int parseIndex(String input) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[1]) - 1;
        return index;
    }

    public boolean isCorrectHelp(String input) {
        if (input.equals("help") || input.equals("help ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user input is formatted into a correct find command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseFindCommand(String input) {
        String[] command = input.split(" ");
        if (input.equals("find") || input.equals("find ")) {
            return false;
        } else if (command.length != 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Parses the user input and returns the keyword for a find command.
     *
     * @param input User input.
     * @return Keyword for find.
     */
    public String parseKeyword(String input) {
        String[] command = input.split(" ");
        return command[1];
    }
}
