package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class Parser is the tool that helps Danh's Duke understand the command from user.
 */
class Parser {

    /**
     * Is the main method of Parser, which takes in a command line
     * ans returns appropriate Command (object).
     *
     * @param commandLine The command line aentered by user.
     * @param taskList    The taskList of the Duke that wants to understand this command line
     * @return the correct command for Duke to execute.
     */
    public static Command parse(String commandLine, ArrayList<Task> taskList) {
        if (commandLine.startsWith("list")) {
            return getListCommand(commandLine);
        } else if (commandLine.startsWith("bye")) {
            return getByeCommand(commandLine);
        } else if (commandLine.startsWith("done ")) {
            return getDoneCommand(commandLine, taskList);
        } else if (commandLine.startsWith("delete ")) {
            return getDeleteCommand(commandLine, taskList);
        } else if (commandLine.startsWith("todo ")) {
            return getTodoCommand(commandLine);
        } else if (commandLine.startsWith("deadline ")) {
            return getDeadlineCommand(commandLine);
        } else if (commandLine.startsWith("event ")) {
            return getEventCommand(commandLine);
        } else if (commandLine.startsWith("myTaskToday")) {
            return getMyTaskTodayCommand(commandLine);
        } else if (commandLine.startsWith("myTaskOn ")) {
            return getMyTaskOnCommand(commandLine);
        } else if (commandLine.startsWith("find ")) {
            return getFindCommand(commandLine);
        } else if (commandLine.startsWith("reminder")) {
            return getReminderCommand(commandLine);
        } else {
            return new Command("executeFalseCommand", "");
        }
    }

    /**
     * Returns the suitable list command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getListCommand(String commandLine) {
        if (commandLine.length() != 4) {
            return new Command("executeFalseCommand", "list");
        }
        return new Command("list", "");
    }

    /**
     * Returns the suitable bye command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getByeCommand(String commandLine) {
        if (commandLine.length() != 3) {
            return new Command("executeFalseCommand", "bye");
        }
        return new Command("bye", "");
    }

    /**
     * Returns the suitable done command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getDoneCommand(String commandLine, ArrayList<Task> taskList) {
        boolean isInvalidForm = commandLine.length() == 5 || !isNumeric(commandLine.substring(5));
        if (isInvalidForm) {
            return new Command("executeFalseCommand", "done");
        }
        boolean isInvalidIndex = Integer.parseInt(commandLine.substring(5)) > taskList.size();
        if (isInvalidIndex) {
            return new Command("executeFalseCommand", "done");
        }
        return new Command("done", commandLine.substring(5));
    }

    /**
     * Returns the suitable delete command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getDeleteCommand(String commandLine, ArrayList<Task> taskList) {
        boolean isInvalidForm = commandLine.length() == 7 || !isNumeric(commandLine.substring(7));
        if (isInvalidForm) {
            return new Command("executeFalseCommand", "delete");
        }
        boolean isInvalidIndex = Integer.parseInt(commandLine.substring(7)) > taskList.size();
        if (isInvalidIndex) {
            return new Command("executeFalseCommand", "delete");
        }
        return new Command("delete", commandLine.substring(7));
    }

    /**
     * Returns the suitable todo command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getTodoCommand(String commandLine) {
        if (commandLine.length() == 5) {
            return new Command("executeFalseCommand", "todo");
        }
        return new Command("todo", commandLine.substring(5));
    }

    /**
     * Returns the suitable deadline command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getDeadlineCommand(String commandLine) {
        boolean isInvalidForm = commandLine.length() == 9 || !commandLine.contains("/by ");
        if (isInvalidForm) {
            return new Command("executeFalseCommand", "deadline");
        }
        boolean isEmptyDeadlineTime = commandLine.indexOf("/by ") + 4 == commandLine.length();
        if (isEmptyDeadlineTime) {
            return new Command("executeFalseCommand", "deadline");
        }
        try {
            LocalDateTime.parse(commandLine.substring(commandLine.indexOf("/by ") + 4),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            return new Command("executeFalseCommand", "myTaskOn");
        }
        return new Command("deadline", commandLine.substring(9));
    }

    /**
     * Returns the suitable event command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getEventCommand(String commandLine) {
        boolean isInvalidForm = commandLine.length() == 6 || !commandLine.contains("/at ");
        if (isInvalidForm) {
            return new Command("executeFalseCommand", "event");
        }
        if (commandLine.indexOf("/at ") + 4 == commandLine.length()) {
            return new Command("executeFalseCommand", "event");
        }
        try {
            LocalDateTime.parse(commandLine.substring(commandLine.indexOf("/at ") + 4),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            return new Command("executeFalseCommand", "myTaskOn");
        }
        return new Command("event", commandLine.substring(6));
    }

    /**
     * Returns the suitable myTaskToday command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getMyTaskTodayCommand(String commandLine) {
        if (commandLine.length() != 11) {
            return new Command("executeFalseCommand", "myTaskToday");
        }
        return new Command("myTaskToday", "");
    }

    /**
     * Returns the suitable myTaskOn command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getMyTaskOnCommand(String commandLine) {
        if (commandLine.length() == 9) {
            return new Command("executeFalseCommand", "myTaskOn");
        }
        try {
            LocalDateTime.parse(commandLine.substring(9) + " 00:00",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            return new Command("executeFalseCommand", "myTaskOn");
        }
        return new Command("myTaskOn", commandLine.substring(9) + " 00:00");
    }

    /**
     * Returns the suitable find command.
     *
     * @param commandLine the commandLine to read
     * @return the suitable Command Object
     */
    private static Command getFindCommand(String commandLine) {
        if (commandLine.length() == 5) {
            return new Command("executeFalseCommand", "find");
        }
        return new Command("find", commandLine.substring(5));
    }

    /**
     * Returns the suitable reminder command.
     *
     * @param commandLine the command line of a reminder command
     * @return the suitable Command Object
     */
    private static Command getReminderCommand(String commandLine) {
        if (commandLine.length() != 8) {
            return new Command("executeFalseCommand", "reminder");
        }
        return new Command("reminder", "");
    }

    /**
     * (Helper function) Checks if the index number format given by command line is correct or not.
     *
     * @param strNum the String that we want to check contains number or not.
     * @return the answer in form of boolean.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
