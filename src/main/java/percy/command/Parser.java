package percy.command;

import percy.exception.PercyException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class Parser {
    public static String fullCmd;

    public Parser(String fullCmd) {
        this.fullCmd =  fullCmd.trim().strip();
    }

    public Command getCommand() {
        String command = this.fullCmd.split(" ", 2)[0];
        switch (command) {
        case TodoCommand.COMMAND:
            return new TodoCommand(getTodoDescription());
        case EventCommand.COMMAND:
            return new EventCommand(getEventDescription(), getEventDate(), getEventTime());
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(getDeadlineDescription(), getDeadlineDate(), getDeadlineTime());
        case DoneCommand.COMMAND:
            return new DoneCommand(this.getTaskNumber());
        case ListCommand.COMMAND:
            return new ListCommand();
        case DeleteCommand.COMMAND:
            return new DeleteCommand(this.getTaskNumber());
        default:
            return new UnknownCommand();
        }
    }

    public static int getTaskNumber() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String TaskNumber = splitCommand[1].trim();
        return Integer.valueOf(TaskNumber);
    }

    public static String getTodoDescription()  {
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = "";
        if (splitCommand.length == 2) {
            description = splitCommand[1].trim();
        }
        return description;
    }

    public static String getEventDescription() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
        String description = args[0].trim();
        return description;
    }

    public static LocalDate getEventDate() {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ",2);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new PercyException("The description or event date/time of an event cannot be empty.")
        }
    }

    public static String getDeadlineDescription() { // same as EventDescription
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = splitCommand[1]
                .substring(0, splitCommand[1].indexOf("/"))
                .trim();
        return description;
    }

    public static String getDeadlineDate() { // same as getEventDate
        String[] splitCommand = fullCmd.split(" ", 2);
        String date = splitCommand[1]
                .substring(splitCommand[1].indexOf("/") + 4, splitCommand[1].length())
                .trim();
        return date;
    }
}
