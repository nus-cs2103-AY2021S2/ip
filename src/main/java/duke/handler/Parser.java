package duke.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeDateTimeException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIdkException;
import duke.exceptions.DukeInvalidDesException;
import duke.exceptions.DukeMissingDesException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parser class that parses inputs and throws errors for commands that cannot be comprehended.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");

    /**
     * Parses data into respective task.
     * @param dataInput Input in data file.
     * @return Task represented by data.
     */
    public static Task parseFromData(String dataInput) {
        String[] splitInputs = dataInput.split("\\|", -1);
        Task task;
        switch (splitInputs[0]) {
        case "T":
            task = new Todo(splitInputs[2]);
            break;
        case "D":
            task = new Deadline(splitInputs[2], LocalDateTime.parse(splitInputs[3]));
            break;
        case "E":
            task = new Event(splitInputs[2], LocalDateTime.parse(splitInputs[3]));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + splitInputs[0]);
        }
        return splitInputs[1].equals("X")
                ? task.markDone()
                : task;
    }

    /**
     * Parses input from user.
     * @param userInput Input from user.
     * @return CommandHandler to handle command.
     * @throws DukeException
     */
    public static CommandHandler parseFromInput(String userInput) throws DukeException {
        String keywordUppCaps = userInput.toUpperCase().split(" ", -1)[0];
        Queries query;
        CommandHandler commandHandler = null;
        if (Queries.containsValue(keywordUppCaps)) {
            query = Queries.valueOf(keywordUppCaps);
        } else {
            throw new DukeIdkException();
        }

        try {
            switch(query) {
            case BYE:
                commandHandler = new ByeHandler();
                break;
            case TODO:
                String todoDes = userInput.split(" ", 2)[1];
                commandHandler = new TodoHandler(todoDes);
                break;
            case DEADLINE:
                String[] dInfo = userInput.split(" ", 2);
                if (dInfo[1].contains("/by")) {
                    dInfo = dInfo[1].split("/by");
                } else {
                    throw new DukeInvalidDesException(keywordUppCaps);
                }
                LocalDateTime dateTimeBy = LocalDateTime.parse(dInfo[1], DATE_TIME_FORMATTER);
                String deadlineDes = dInfo[0];
                commandHandler = new DeadlineHandler(deadlineDes, dateTimeBy);
                break;
            case EVENT:
                String[] eInfo = userInput.split(" ", 2);
                if (eInfo[1].contains("/at")) {
                    eInfo = eInfo[1].split(("/at"));
                } else {
                    throw new DukeInvalidDesException(keywordUppCaps);
                }
                LocalDateTime dateTimeAt = LocalDateTime.parse(eInfo[1], DATE_TIME_FORMATTER);
                String eventDes = eInfo[0];
                commandHandler = new EventHandler(eventDes, dateTimeAt);
                break;
            case FIND:
                String findWord = userInput.split(" ", 2)[1];
                commandHandler = new FindHandler(findWord);
                break;
            case DONE:
                int doneNum = Integer.parseInt(userInput.split(" ")[1]);
                commandHandler = new DoneHandler(doneNum);
                break;
            case DELETE:
                int deleteNum = Integer.parseInt(userInput.split(" ")[1]);
                commandHandler = new DeleteHandler(deleteNum);
                break;
            case LIST:
                commandHandler = new ListHandler();
                break;
            default:
                System.out.println("Unknown query in parser.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingDesException(query.toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeInvalidDesException(query.toString());
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException();
        }
        return commandHandler;
    }
}
