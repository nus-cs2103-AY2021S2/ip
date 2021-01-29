package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private LocalDateTime parseDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        return dateTime;
    }

    /**
     * Parses the user input and returns the command they have entered.
     *
     * @param input User input.
     * @return Command word entered by user.
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

    /**
     * Parses the user input and returns the description of a To-do task.
     *
     * @param input User input.
     * @return To-do description.
     */
    public String parseTodoDescripton(String input) {
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

    /**
     * Parses the user input and returns the description of a Deadline task.
     *
     * @param input User input.
     * @return Deadline description.
     */
    public String parseDeadlineDescription(String input) {
        String[] details = input.split(" /by ");
        return details[0].substring(9);
    }

    /**
     * Parses the user input and returns the deadline of a Deadline task.
     *
     * @param input User input.
     * @return LocalDateTime object containing deadline of Deadline task.
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

    /**
     * Parses the user input and returns the description of an Event task.
     *
     * @param input User input.
     * @return Event description.
     */
    public String parseEventDescription(String input) {
        String[] details = input.split(" /from ");
        return details[0].substring(6);
    }

    /**
     * Parses the user input and returns the starting date of an Event task.
     *
     * @param input User input.
     * @return LocalDateTime object containing starting date of an Event task.
     */
    public LocalDateTime parseEventStartDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[0]);
    }

    /**
     * Parses the user input and returns the ending date of an Event task.
     *
     * @param input User input.
     * @return LocalDateTime object containing ending date of an Event task.
     */
    public LocalDateTime parseEventEndDate(String input) {
        String[] details = input.split(" /from ");
        String[] dates = details[1].split(" /to ");
        return parseDate(dates[1]);
    }

    /**
     * Checks if the user input is formatted into a correct list command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean isCorrectList(String input) {
        if (input.equals("list") || input.equals("list ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user input is formatted into a correct index calling command.
     *
     * @param input User input.
     * @param size Number of items on the task list.
     * @return Boolean.
     */
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

    /**
     * Parses the user input and returns the index of the index calling command.
     *
     * @param input User input.
     * @return Index called.
     */
    public int parseIndex(String input) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[1]) - 1;
        return index;
    }

    /**
     * Checks if the user input is formatted into a correct help command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean isCorrectHelp(String input) {
        if (input.equals("help") || input.equals("help ")) {
            return true;
        } else {
            return false;
        }
    }
}
