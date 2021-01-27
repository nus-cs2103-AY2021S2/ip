package duke;

import exception.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Handles duke operations and parses input commands.
 */
public class Duke {
    /**
     * DukeResponse object to handle responses.
     */
    private final DukeResponse dukeResponse;

    /**
     * DukeStorage object to handle history files.
     */
    private final DukeStorage dukeStorage;
    /**
     * DukeTaskList to handle task list operations.
     */
    private final DukeTaskList dukeTaskList;

    /**
     * Datetime parsed format.
     */
    public static DateTimeFormatter format = DateTimeFormatter
            .ofPattern("dd/MM/yyyy, hh:mma", Locale.US);

    /**
     * Instantiates a new duke.
     *
     * @throws IOException the io exception
     */
//TODO: handle file not found exception
    public Duke() throws IOException, DukeException {
        this.dukeStorage = new DukeStorage();
        this.dukeTaskList = new DukeTaskList(this.dukeStorage.load());
        this.dukeResponse = new DukeResponse();
    }

    /**
     * Parses given input and sets a new current message based on input.
     *
     * @param input the input message.
     * @return integer. 0 represents an terminating entry, 1 represents a continuing entry.
     * @throws DukeException the duke exception
     * @throws IOException   the io exception
     */
    public int parse(String input) throws DukeException, IOException {
        String[] parsedInput = input.split("\\s+", 2);
        switch (parsedInput[0]) {
        case (""):
            throw new DukeNoInputException();
        case ("todo"): {
            if (parsedInput.length < 2 || parsedInput[1].length() == 0) {
                throw new DukeMissingArgumentsException();
            } else {
                Todo currTodo = new Todo(parsedInput[1]);
                dukeTaskList.add(currTodo);
                dukeResponse.addTask(currTodo, dukeTaskList.size());
            }
            break;
        }
        case ("event"): {
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            String[] args = parsedInput[1].split("/at", 2);
            if (args.length < 2 || args[1].length() == 0) {
                throw new DukeMissingArgumentsException();
            }
            try {
                LocalDateTime dateTime = LocalDateTime.parse(args[1].substring(1), format);
                Event currEvent = new Event(args[0], dateTime);
                dukeTaskList.add(currEvent);
                dukeResponse.addTask(currEvent, dukeTaskList.size());
                break;
            } catch (DateTimeParseException e) {
                throw new DukeDateFormatException();
            }
        }
        case ("deadline"): {
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            String[] args = parsedInput[1].split("/by", 2);
            if (args.length < 2 || args[1].length() == 0) {
                throw new DukeMissingArgumentsException();
            }
            try {
                LocalDateTime dateTime = LocalDateTime.parse(args[1].substring(1), format);
                Deadline currDeadline = new Deadline(args[0], dateTime);
                dukeTaskList.add(currDeadline);
                dukeResponse.addTask(currDeadline, dukeTaskList.size());
                break;
            } catch (DateTimeParseException e) {
                throw new DukeDateFormatException();
            }
        }
        case ("delete"):
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            int deleteIndex = Integer.parseInt(parsedInput[1]) - 1;
            if (deleteIndex > dukeTaskList.size()) {
                throw new DukeMissingTaskException();
            }
            Task deletedTask = dukeTaskList.get(deleteIndex);
            dukeTaskList.remove(deleteIndex);
            dukeResponse.deleteTask(deletedTask, dukeTaskList.size());
            break;
        case ("list"):
            dukeResponse.listTasks(dukeTaskList, false);
            break;
        case ("find"):
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            String subString = parsedInput[1];
            ArrayList<Task> searchedList = new ArrayList<>();
            for (Task t : dukeTaskList.getTaskList()) {
                if (t.getDescription().contains(subString)) {
                    searchedList.add(t);
                }
            }
            DukeTaskList dukeSearchTaskList = new DukeTaskList(searchedList);
            dukeResponse.listTasks(dukeSearchTaskList, true);
            break;
        case ("done"): {
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            int index = Integer.parseInt(parsedInput[1]) - 1;
            if (index >= dukeTaskList.size()) {
                throw new DukeMissingTaskException();
            }
            Task currTask = dukeTaskList.get(index);
            currTask.markAsDone();
            dukeResponse.markAsDone(currTask);
            break;
        }
        case ("bye"):
            dukeResponse.farewell();
            dukeStorage.unload(dukeTaskList);
            return 0;
        default:
            throw new DukeUnknownInputException();
        }
        return 1;
    }
}
