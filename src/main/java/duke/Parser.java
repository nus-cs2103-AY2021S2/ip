package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class Parser is the tool that helps Danh's Duke understand the command from user.
 */
class Parser {

    /**
     * This method is the main method of Parser, which takes in a command line
     * ans returns appropriate Command (object).
     *
     * @param commandLine The command line aentered by user.
     * @param taskList    The taskList of the Duke that wants to understand this command line
     * @return the correct command for Duke to execute.
     */
    public static Command parse(String commandLine, ArrayList<Task> taskList) {
        if (commandLine.startsWith("list")) {
            if (commandLine.length() != 4) {
                return new Command("executeFalseCommand", "list");
            } else {
                return new Command("list", "");
            }
        } else if (commandLine.startsWith("bye")) {
            if (commandLine.length() != 3) {
                return new Command("executeFalseCommand", "bye");
            } else {
                return new Command("bye", "");
            }
        } else if (commandLine.startsWith("done ")) {
            if (commandLine.length() == 5 || !isNumeric(commandLine.substring(5))) {
                return new Command("executeFalseCommand", "done");
            } else if (Integer.parseInt(commandLine.substring(5)) > taskList.size()) {
                return new Command("executeFalseCommand", "done");
            } else {
                return new Command("done", commandLine.substring(5));
            }
        } else if (commandLine.startsWith("delete ")) {
            if (commandLine.length() == 7 || !isNumeric(commandLine.substring(7))) {
                return new Command("executeFalseCommand", "delete");
            } else if (Integer.parseInt(commandLine.substring(7)) > taskList.size()) {
                return new Command("executeFalseCommand", "delete");
            } else {
                return new Command("delete", commandLine.substring(7));
            }
        } else if (commandLine.startsWith("todo ")) {
            if (commandLine.length() == 5) {
                return new Command("executeFalseCommand", "todo");
            } else {
                return new Command("todo", commandLine.substring(5));
            }
        } else if (commandLine.startsWith("deadline ")) {
            if (commandLine.length() == 9 || !commandLine.contains("/by ")) {
                return new Command("executeFalseCommand", "deadline");
            } else if (commandLine.indexOf("/by ") + 4 == commandLine.length()) {
                return new Command("executeFalseCommand", "deadline");
            } else {
                try {
                    LocalDateTime.parse(commandLine.substring(commandLine.indexOf("/by ") + 4),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    return new Command("executeFalseCommand", "myTaskOn");
                }
                return new Command("deadline", commandLine.substring(9));
            }
        } else if (commandLine.startsWith("event ")) {
            if (commandLine.length() == 6 || !commandLine.contains("/at ")) {
                return new Command("executeFalseCommand", "event");
            } else if (commandLine.indexOf("/at ") + 4 == commandLine.length()) {
                return new Command("executeFalseCommand", "event");
            } else {
                try {
                    LocalDateTime.parse(commandLine.substring(commandLine.indexOf("/at ") + 4),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    return new Command("executeFalseCommand", "myTaskOn");
                }
                return new Command("event", commandLine.substring(6));
            }
        } else if (commandLine.startsWith("myTaskToday")) {
            if (commandLine.length() != 11) {
                return new Command("executeFalseCommand", "myTaskToday");
            } else {
                return new Command("myTaskToday", "");
            }
        } else if (commandLine.startsWith("myTaskOn ")) {
            if (commandLine.length() == 9) {
                return new Command("executeFalseCommand", "myTaskOn");
            } else {
                try {
                    LocalDateTime.parse(commandLine.substring(9) + " 00:00",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    return new Command("executeFalseCommand", "myTaskOn");
                }
                return new Command("myTaskOn", commandLine.substring(9) + " 00:00");
            }
        } else if (commandLine.startsWith("find ")) {
            if (commandLine.length() == 5) {
                return new Command("executeFalseCommand", "find");
            } else {
                return new Command("find", commandLine.substring(5));
            }
        } else {
            return new Command("executeFalseCommand", "");
        }
    }

    /**
     * This is helper function to help Parser checks if the index number format given by command line is correct or not.
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
