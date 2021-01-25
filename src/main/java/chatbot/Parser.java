package chatbot;

import chatbot.commands.*;
import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.InvalidCommandTypeException;
import chatbot.exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static String[] CommandTypeArray = {
            "bye", "list", "todo", "deadline", "event", "done", "delete"
    };
    private static LocalDate formatDate(String date) throws InvalidDateFormatException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
            return LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    public static ChatBotCommand parse(String command) throws ChatBotException {
        String[] words = command.strip().split(" ", 2);
        String commandType = words[0];
        boolean isValidCommand = false;
        for (String expectedCommand : CommandTypeArray) {
            if (commandType.equals(expectedCommand)) {
                isValidCommand = true;
                break;
            }
        }
        if (!isValidCommand) {
            throw new InvalidCommandTypeException();
        }

        try {
            switch (commandType) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "todo":
                    return new TodoCommand(words[1]);
                case "deadline":
                    String[] deadlineArray = words[1].split(" /by ");
                    String ddlName = deadlineArray[0].strip();
                    String deadline = deadlineArray[1].strip();
                    return new DeadlineCommand(ddlName, formatDate(deadline));
                case "event":
                    String[] timeArray = words[1].split(" /at ");
                    String eventName = timeArray[0].strip();
                    String time = timeArray[1];
                    return new EventCommand(eventName, time);
                case "done":
                    return new DoneCommand(Integer.parseInt(words[1]));
                case "delete":
                    return new DeleteCommand(Integer.parseInt(words[1]));
            }
        } catch (ChatBotException e) {
            throw e;
        }
        return null;
    }
}
