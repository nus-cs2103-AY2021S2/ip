package percy.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import percy.exception.PercyException;
import percy.ui.Ui;

public class Parser {
    private static String fullCmd;

    public Parser(String fullCmd) {
        this.fullCmd = fullCmd.trim().strip();
    }

    public Command getCommand() throws PercyException {
        String command = this.fullCmd.split(" ", 2)[0];
        switch (command) {
        case TodoCommand.COMMAND:
            return new TodoCommand(getTodoDescription());
        case EventCommand.COMMAND:
            return new EventCommand(getEventDescription(), getEventDate(), getEventTime());
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(getDeadlineDescription(), getDeadlineDate(), getDeadlineTime());
        case DoneCommand.COMMAND:
            return new DoneCommand(getTaskNumber());
        case ListCommand.COMMAND:
            return new ListCommand();
        case DeleteCommand.COMMAND:
            return new DeleteCommand(getTaskNumber());
        case ByeCommand.COMMAND:
            return new ByeCommand();
        case FindCommand.COMMAND:
            return new FindCommand(getKeyword());
        default:
            return new UnknownCommand();
        }
    }

    private static int getTaskNumber() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String taskNumber = splitCommand[1].trim();
        return Integer.valueOf(taskNumber);
    }

    private static String getTodoDescription() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = "";
        if (splitCommand.length == 2) {
            description = splitCommand[1].trim();
        }
        return description;
    }

    private static String getEventDescription() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String description = args[0].trim();
            return description;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or  date/time of an event cannot be empty.");
            arr.addAll(EventCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static LocalDate getEventDate() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            return date;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or date/time of an event cannot be empty.");
            arr.addAll(EventCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        } catch (DateTimeParseException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The date and time format of an event is wrong.");
            arr.addAll(EventCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static LocalTime getEventTime() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalTime time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return time;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or date/time of an event cannot be empty.");
            arr.addAll(EventCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        } catch (DateTimeParseException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The date and time format of an event is wrong.");
            arr.addAll(EventCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static String getDeadlineDescription() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String description = args[0].trim();
            return description;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or date/time of a deadline cannot be empty.");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static LocalDate getDeadlineDate() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            return date;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or date/time of a deadline cannot be empty.");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        } catch (DateTimeParseException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The date and time format of a deadline is wrong.");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static LocalTime getDeadlineTime() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalTime time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return time;
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The description or date/time of a deadline cannot be empty.");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        } catch (DateTimeParseException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The date and time format of a deadline is wrong.");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }

    private static String getKeyword() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            return splitCommand[1].trim().toLowerCase();
        } catch (IndexOutOfBoundsException e) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("OOPS!!! The keyword is missing.");
            arr.addAll(FindCommand.USAGE_GUIDE);
            throw new PercyException(Ui.makeMsg(arr));
        }
    }
}