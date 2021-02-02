package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private final int DEADLINE_SUB = 9;
    private final int EVENT_SUB = 6;

    private LocalDateTime parseDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        return dateTime;
    }

    /**
     * Parses user input and returns the command.
     *
     * @param input User input.
     * @return Command word.
     */
    public String parseCommand(String input) {
        String[] command = input.split(" ");
        return command[0];
    }

    /**
     * Checks if the user input is formatted into a correct To-do task.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseTodo(String input) {
        String[] command = input.split(" ");
        if (command.length == 1) {
            return false;
        } else if (command[1].equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Parses the user input and returns the description of a To-do task.
     *
     * @param input User input.
     * @return To-do description.
     */
    public String parseTodoDescription(String input) {
        String[] command = input.split(" ");
        String description = "";
        for (int i = 1; i < command.length; i++) {
            description += command[i] + " ";
        }
        return description;
    }

    /**
     * Checks if the user input is formatted into a correct Deadline task.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseDeadline(String input) {
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

    /**
     * Parses the user input and returns the description of a Deadline task.
     *
     * @param input User input.
     * @return Deadline description.
     */
    public String parseDeadlineDescription(String input) {
        String[] details = input.split(" /by ");
        return details[0].substring(DEADLINE_SUB);
    }

    /**
     * Parses the user input and returns the deadline of a Deadline task.
     *
     * @param input User input.
     * @return Deadline date.
     */
    public LocalDateTime parseDeadlineDate(String input) {
        String[] details = input.split(" /by ");
        return parseDate(details[1]);
    }

    /**
     * Checks if the user input is formatted into a correct Event task.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseEvent(String input) {
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

    /**
     * Parses the user input and returns the description of an Event task.
     *
     * @param input User input.
     * @return Event description.
     */
    public String parseEventDescription(String input) {
        String[] details = input.split(" /from ");
        return details[0].substring(EVENT_SUB);
    }

    /**
     * Parses the user input and returns the start date of an Event task.
     *
     * @param input User input.
     * @return Event start date.
     */
    public LocalDateTime parseEventStartDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[0]);
    }

    /**
     * Parses the user input and returns the end date of an Event task.
     *
     * @param input User input.
     * @return Event end date.
     */
    public LocalDateTime parseEventEndDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[1]);
    }

    /**
     * Checks if the user input is formatted into a correct List command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseListCommand(String input) {
       return input.equals("list") || input.equals("list ");
    }

    /**
     * Checks if the user input is formatted into a correct index calling command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseIndexCommand(String input, int size) {
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

    /**
     * Parses the user input and returns the index of an index calling command.
     *
     * @param input User input.
     * @return Index.
     */
    public int parseIndex(String input) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[1]) - 1;
        return index;
    }

    /**
     * Checks if the user input is formatted into a correct Help command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseHelpCommand(String input) {
        if (input.equals("help") || input.equals("help ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user input is formatted into a correct Find command.
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
