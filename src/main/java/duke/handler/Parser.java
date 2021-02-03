package duke.handler;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");

    public static Task parseFromData(String dataInput) {
        String[] splitInput = dataInput.split("\\|", -1);
        Task task;
        switch (splitInput[0]) {
        case "T":
            task = new Todo(splitInput[2]);
            break;
        case "D":
            task = new Deadline(splitInput[2], LocalDateTime.parse(splitInput[3]));
            break;
        case "E":
            task = new Event(splitInput[2], LocalDateTime.parse(splitInput[3]));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + splitInput[0]);
        }
        return splitInput[1].equals("X")
                ? task.markDone()
                : task;
    }

    public static CommandHandler parseFromInput(String userInput) throws DukeException {
        String keyword_UC = userInput.toUpperCase().split(" ", -1)[0];
        Queries query;
        CommandHandler commandHandler = null;
        if (Queries.containsValue(keyword_UC)) {
            query = Queries.valueOf(keyword_UC);
        } else {
            throw new DukeIDKException();
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
                    throw new DukeInvalidDesException(keyword_UC);
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
                    throw new DukeInvalidDesException(keyword_UC);
                }
                LocalDateTime dateTimeAt = LocalDateTime.parse(eInfo[1], DATE_TIME_FORMATTER);
                String eventDes = eInfo[0];
                commandHandler = new EventHandler(eventDes, dateTimeAt);
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
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingDesException(query.toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            throw new DukeInvalidDesException(query.toString());
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException();
        }
        return commandHandler;
    }
}
