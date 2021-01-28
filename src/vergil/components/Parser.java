package vergil.components;

import vergil.types.Command;
import vergil.types.CommandType;
import vergil.types.VergilException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}

    public Command parse(String command) throws VergilException {
        try {
            switch (command.split(" ")[0]) {
            case "bye":
                return new Command(
                        CommandType.BYE,
                        null,
                        null,
                        0);

            case "list":
                return new Command(
                        CommandType.LIST,
                        null,
                        null,
                        0);

            case "done":
                return new Command(
                        CommandType.DONE,
                        null,
                        null,
                        Integer.parseInt(command.split(" ")[1]));

            case "delete":
                return new Command(
                        CommandType.DELETE,
                        null,
                        null,
                        Integer.parseInt(command.split(" ")[1]));

            case "todo":
                return new Command(
                        CommandType.TODO,
                        command.split(" /by ")[0].split(" ", 2)[1],
                        null,
                        0);

            case "deadline":
                return new Command(
                        CommandType.DEADLINE,
                        command.split(" /by ")[0].split(" ", 2)[1],
                        LocalDateTime.parse(command.split(" /by ")[1], DateTimeFormatter.ofPattern("d/M/y HHmm")),
                        0);

            case "event":
                return new Command(
                        CommandType.EVENT,
                        command.split(" /at ")[0].split(" ", 2)[1],
                        LocalDateTime.parse(command.split(" /at ")[1], DateTimeFormatter.ofPattern("d/M/y HHmm")),
                        0);

            default:
                throw new VergilException("Unable to resolve command.");
            }
         } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new VergilException("Command format is invalid.");
        }
    }
}
