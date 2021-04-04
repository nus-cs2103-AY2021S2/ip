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
     *
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
     *
     * @param userInput Input from user.
     * @return CommandHandler to handle command.
     * @throws DukeException When input cannot be parsed, appropriate Duke Exception thrown.
     */
    public static CommandHandler parseFromInput(String userInput) throws DukeException {
        String keywordUppCaps = userInput.toUpperCase().split(" ", -1)[0];
        Queries query;
        CommandHandler commandHandler;
        if (Queries.containsValue(keywordUppCaps)) {
            query = Queries.valueOf(keywordUppCaps);
        } else {
            throw new DukeIdkException();
        }

        commandHandler = handlerFromQueryInput(query, userInput, keywordUppCaps);
        return commandHandler;
    }

    private static CommandHandler handlerFromQueryInput(Queries query, String userInput, String keywordUppCaps)
            throws DukeException {
        CommandHandler commandHandler = null;
        try {
            switch (query) {
            case BYE:
                commandHandler = parseBye();
                break;
            case TODO:
                commandHandler = parseTodo(userInput);
                break;
            case DEADLINE:
                commandHandler = parseDeadline(userInput, keywordUppCaps);
                break;
            case EVENT:
                commandHandler = parseEvent(userInput, keywordUppCaps);
                break;
            case FIND:
                commandHandler = parseFind(userInput);
                break;
            case DONE:
                commandHandler = parseDone(userInput);
                break;
            case DELETE:
                commandHandler = parseDelete(userInput);
                break;
            case LIST:
                commandHandler = parseList();
                break;
            case SCHEDULE:
                commandHandler = parseSchedule(userInput, keywordUppCaps);
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

    private static ByeHandler parseBye() {
        return new ByeHandler();
    }

    private static TodoHandler parseTodo(String userInput) {
        String todoDes = userInput.split(" ", 2)[1];
        return new TodoHandler(todoDes);
    }

    private static DeadlineHandler parseDeadline(String userInput,
                                                 String keywordUppCaps) throws DukeInvalidDesException {
        String[] dInfo = userInput.split(" ", 2);
        if (dInfo[1].contains("/by")) {
            dInfo = dInfo[1].split("/by");
        } else {
            throw new DukeInvalidDesException(keywordUppCaps);
        }
        LocalDateTime dateTimeBy = LocalDateTime.parse(dInfo[1], DATE_TIME_FORMATTER);
        String deadlineDes = dInfo[0];
        return new DeadlineHandler(deadlineDes, dateTimeBy);
    }

    private static EventHandler parseEvent(String userInput,
                                           String keywordUppCaps) throws DukeInvalidDesException {
        String[] eInfo = userInput.split(" ", 2);
        if (eInfo[1].contains("/at")) {
            eInfo = eInfo[1].split(("/at"));
        } else {
            throw new DukeInvalidDesException(keywordUppCaps);
        }
        LocalDateTime dateTimeAt = LocalDateTime.parse(eInfo[1], DATE_TIME_FORMATTER);
        String eventDes = eInfo[0];
        return new EventHandler(eventDes, dateTimeAt);
    }

    private static FindHandler parseFind(String userInput) {
        String findWord = userInput.split(" ", 2)[1];
        return new FindHandler(findWord);
    }

    private static DoneHandler parseDone(String userInput) {
        int doneNum = Integer.parseInt(userInput.split(" ")[1]);
        return new DoneHandler(doneNum);
    }

    private static DeleteHandler parseDelete(String userInput) {
        int deleteNum = Integer.parseInt(userInput.split(" ")[1]);
        return new DeleteHandler(deleteNum);
    }

    private static ListHandler parseList() {
        return new ListHandler();
    }

    private static ScheduleHandler parseSchedule(String userInput, String keywordUppCaps) throws DukeException {
        String[] sInfo = userInput.split(" ", 4);
        int numOfTimes = Integer.parseInt(sInfo[1]);
        String frequency = sInfo[2];
        checkFreqIsWeekly(frequency, keywordUppCaps);
        String command = sInfo[3];
        CommandHandler toScheduleHandler = parseFromInput(command);
        checkCommandCanSchedule(toScheduleHandler, keywordUppCaps);
        return new ScheduleHandler(toScheduleHandler, frequency, numOfTimes);
    }

    private static void checkFreqIsWeekly(String frequency, String keywordUppCaps) throws DukeInvalidDesException {
        if (!frequency.equalsIgnoreCase("weekly")) {
            throw new DukeInvalidDesException(keywordUppCaps);
        }
    }
    private static void checkCommandCanSchedule(CommandHandler toScheduleHandler,
                                                String keywordUppCaps) throws DukeInvalidDesException {
        if (!(toScheduleHandler instanceof DeadlineHandler || toScheduleHandler instanceof EventHandler)) {
            throw new DukeInvalidDesException(keywordUppCaps);
        }
    }
}
