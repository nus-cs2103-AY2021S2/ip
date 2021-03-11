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
        if (command[0].equals("contact") && command.length > 1) {
            return command[0] + " " + command[1];
        }
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
        assert command[0].equals("todo") : "Incorrect command parsing";
        if (command.length == 1) {
            return false;
        }
        if (command[1].equals("")) {
            return false;
        }
        return true;
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
        assert command[0].equals("deadline") : "Incorrect command parsing";
        if (command.length == 1) {
            return false;
        }
        if (command[1].equals("/by")) {
            return false;
        }
        String[] details = input.split(" /by ");
        if (details.length != 2) {
            return false;
        }
        return true;
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
        assert command[0].equals("event") : "Incorrect command parsing";
        if (command.length == 1) {
            return false;
        }
        if (command[1].equals("/from")) {
            return false;
        }
        String[] details = input.split(" /from ");
        if (details.length != 2) {
            return false;
        }
        String[] dates = details[1].split(" /to ");
        if (dates.length != 2) {
            return false;
        }
        return true;
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
     * @param size Size of task list.
     * @return Boolean.
     */
    public boolean canParseIndexCommand(String input, int size) {
        String[] command = input.split(" ");
        if (input.equals("done") || input.equals("done ")) {
            return false;
        }
        if (command.length != 2) {
            return false;
        }
        try {
            int index = Integer.valueOf(command[1]) - 1;
            if (index < 0 || index >= size) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the user input and returns the index of an index calling command.
     *
     * @param input User input.
     * @param size Size of task list.
     * @return Index.
     */
    public int parseIndex(String input, int size) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[1]) - 1;
        assert index >= 0 && index < size: "Index is out of bounds.";
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
        }
        return false;
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
        }
        if (command.length != 2) {
            return false;
        }
        return true;
    }

    /**
     * Parses the user input and returns the keyword for a find command.
     *
     * @param input User input.
     * @return Keyword for find.
     */
    public String parseKeyword(String input) {
        String[] command = input.split(" ");
        assert command[0].equals("find") : "Incorrect command parsing";
        return command[1];
    }

    /**
     * Checks if user input is formatted for an Add Contact command with a valid name input.
     *
     * @param input User input.
     * @return Boolean.
     */
    private boolean canParseAddName(String input) {
        String[] command = input.split("/name");
        if (command.length != 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if user input is formatted for an Add Contact command with a valid number input.
     *
     * @param input User input.
     * @return Boolean.
     */
    private boolean canParseAddNumber(String input) {
        String[] command = input.split("/number ");
        if (command.length != 2) {
            return false;
        }
        try {
            String[] details = command[1].split("/address ");
            String[] numInfo = details[0].split(" ");
            Integer.valueOf(numInfo[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if user input is formatted for Add Contact command with a valid address input.
     *
     * @param input User input.
     * @return Boolean.
     */
    private boolean canParseAddAddress(String input) {
        String[] command = input.split("/address ");
        if (command.length != 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if user input is formatted correctly for an Add Contact command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseAddContactCommand(String input) {
        boolean isContactCommand = false;
        if (parseCommand(input).equals("contact add")) {
            isContactCommand = true;
        }
        boolean hasValidName = canParseAddName(input);
        boolean hasValidNumber = canParseAddNumber(input);
        boolean hasValidAddress = canParseAddAddress(input);
        boolean hasEitherNumberOrAddress = hasValidNumber || hasValidAddress;
        return isContactCommand && hasValidName && hasEitherNumberOrAddress;
    }

    /**
     * Parses the user input and returns the name of a contact.
     *
     * @param input User input.
     * @return Name of contact.
     */
    public String parseAddContactName(String input) {
        String[] command = input.split("/name ");
        String[] details = command[1].split(" /number ");
        if (details.length != 2) {
            details = command[1].split(" /address ");
        }
        String name = details[0];
        return name;
    }

    /**
     * Parses the user input and returns the number of a contact.
     *
     * @param input User input.
     * @return Contact number.
     */
    public int parseAddContactNumber(String input) {
        String[] command = input.split("/number ");
        if (command.length != 2) {
            return 0;
        }
        String[] details = command[1].split("/address ");
        details = details[0].split(" ");
        String numString = details[0];
        int number = Integer.valueOf(numString);
        return number;
    }

    /**
     * Parses the user input and returns the address of a contact.
     *
     * @param input User input.
     * @return Contact address.
     */
    public String parseAddContactAddress(String input) {
        String[] command = input.split("/address ");
        if (command.length != 2) {
            return "";
        }
        String address = command[1];
        return address;
    }

    /**
     * Checks if user input is formatted as a correct Contact List command.
     *
     * @param input User input.
     * @return Boolean.
     */
    public boolean canParseListContactCommand(String input) {
        return input.equals("contact list");
    }

    /**
     * Checks if user input has a valid index in the correct format for an contact command that uses an index.
     *
     * @param input User input.
     * @param size Size of contact list.
     * @return Boolean.
     */
    private boolean canParseContactIndexCommand(String input, int size) {
        String[] command = input.split(" ");
        if (command.length < 3) {
            return false;
        }
        try {
            int index = Integer.valueOf(command[2]);
            if (index > 0 && index <= size) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if user input is formatted correctly as a Contact Delete command.
     *
     * @param input User input.
     * @param size Size of contact list.
     * @return Boolean.
     */
    public boolean canParseContactDeleteCommand(String input, int size) {
        String[] command = input.split(" ");
        boolean isValidCommand = false;
        if (parseCommand(input).equals("contact delete") && command.length == 3) {
            isValidCommand = true;
        }
        boolean hasValidIndex = canParseContactIndexCommand(input, size);
        return isValidCommand && hasValidIndex;
    }

    /**
     * Parses the user input and returns the index of the contact to be deleted.
     *
     * @param input User input.
     * @param size Size of contact list.
     * @return Index of contact to be deleted.
     */
    public int parseContactDeleteCommand(String input, int size) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[2]) - 1;
        assert index <= 0 && index < size : "Index is out of bounds";
        return index;
    }

    /**
     * Checks if the user input is formatted to a correct Edit Contact command.
     *
     * @param input User input.
     * @param size Size of contact list.
     * @return Boolean.
     */
    public boolean canParseEditContactCommand(String input, int size) {
        String[] command = input.split(" ");
        if (command.length < 4) {
            return false;
        }
        boolean hasValidIndex = canParseContactIndexCommand(input, size);
        boolean hasValidField = false;
        boolean hasValidEdit = true;
        boolean isValidCommand = false;
        if (command[3].equals("/name") || command[3].equals("/number") || command[3].equals("/address")) {
            hasValidField = true;
        }
        if (command[0].equals("contact") && command[1].equals("edit")) {
            isValidCommand = true;
        }
        if (hasValidField && parseContactEditField(input).equals("/number")) {
            try {
                Integer.valueOf(parseContactEditChange(input));
                hasValidEdit = true;
            } catch (NumberFormatException e) {
                hasValidEdit = false;
            }
        }
        if (hasValidField && parseContactEditField(input).equals("/name")) {
            hasValidEdit = command.length >= 5;
        }
        return hasValidIndex && hasValidField && hasValidEdit && isValidCommand;
    }

    /**
     * Parses the user input and returns the index of the contact to be edited.
     *
     * @param input User input.
     * @param size Size of contact list.
     * @return Index of contact to be edited.
     */
    public int parseContactEditIndex(String input, int size) {
        String[] command = input.split(" ");
        int index = Integer.valueOf(command[2]) - 1;
        assert index >= 0 && index < size : "Index is out of bounds";
        return index;
    }

    /**
     * Parses the user input and returns the field of the contact to be edited.
     *
     * @param input User input.
     * @return Field to be edited.
     */
    public String parseContactEditField(String input) {
        String[] command = input.split(" ");
        return command[3];
    }

    /**
     * Parses the user input and returns the change to be made to the contact.
     *
     * @param input User input.
     * @return Change to be made.
     */
    public String parseContactEditChange(String input) {
        String field = parseContactEditField(input) + " ";
        String[] command = input.split(field);
        if (command.length == 2) {
            return command[1];
        }
        return "";
    }
}