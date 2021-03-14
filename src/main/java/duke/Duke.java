package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

import exception.DukeDateFormatException;
import exception.DukeException;
import exception.DukeMissingArgumentsException;
import exception.DukeMissingTaskException;
import exception.DukeNoInputException;
import exception.DukeUnknownInputException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handles duke operations and parses input commands.
 */
public class Duke {
    /**
     * Datetime parsed format.
     */
    private static final DateTimeFormatter format = DateTimeFormatter
            .ofPattern("dd/MM/yyyy, hh:mma", Locale.US);
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
     * Instantiates a new duke.
     *
     * @throws IOException   the io exception
     * @throws DukeException the duke exception
     */
    public Duke() throws IOException, DukeException {
        this.dukeStorage = new DukeStorage();
        this.dukeTaskList = new DukeTaskList(this.dukeStorage.load());
        this.dukeResponse = new DukeResponse();
    }

    /**
     * Parses given input and sets a new current message based on input.
     *
     * @param input the input message.
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception.
     * @throws IOException   the caught io exception.
     */
    public String parse(String input) throws DukeException, IOException {
        String[] parsedInput = input.split("\\s+", 2);
        switch (parsedInput[0]) {
        case (""):
            throw new DukeNoInputException();
            // Fallthrough
        case ("welcome"):
            return handleWelcome();
            // Fallthrough
        case ("todo"): {
            return handleToDo(parsedInput);
            //Fallthrough
        }
        case ("event"): {
            return handleEvent(parsedInput);
            //Fallthrough
        }
        case ("deadline"): {
            return handleDeadline(parsedInput);
            //Fallthrough
        }
        case ("delete"):
            return handleDelete(parsedInput);
            //Fallthrough
        case ("list"):
            return dukeResponse.listTasks(dukeTaskList, false);
            //Fallthrough
        case ("find"):
            return handleFind(parsedInput);
            //Fallthrough
        case ("done"): {
            return handleDone(parsedInput);
            //Fallthrough
        }
        case ("bye"):
            dukeStorage.unload(dukeTaskList);
            return dukeResponse.farewell();
            // Fallthrough
        default:
            throw new DukeUnknownInputException();
            // Fallthrough
        }
    }
    public String handleWelcome() throws DukeException {
        return dukeResponse.welcome();
    }

    /**
     * Handle todo command.
     *
     * @param parsedInput the parsed input
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleToDo(String[] parsedInput) throws DukeException {
        if (parsedInput.length < 2 || parsedInput[1].length() == 0) {
            throw new DukeMissingArgumentsException();
        } else {
            Todo currTodo = new Todo(parsedInput[1]);
            dukeTaskList.add(currTodo);
            return dukeResponse.addTask(currTodo, dukeTaskList.size());
        }
    }

    /**
     * Handle event command.
     *
     * @param parsedInput the parsed input
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleEvent(String[] parsedInput) throws DukeException {
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
            return dukeResponse.addTask(currEvent, dukeTaskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeDateFormatException();
        }
    }

    /**
     * Handle deadline command.
     *
     * @param parsedInput the parsed input
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleDeadline(String[] parsedInput) throws DukeException {
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
            return dukeResponse.addTask(currDeadline, dukeTaskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeDateFormatException();
        }
    }

    /**
     * Handle delete command.
     *
     * @param parsedInput the parsed input
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleDelete(String[] parsedInput) throws DukeException {
        if (parsedInput.length < 2) {
            throw new DukeMissingArgumentsException();
        }
        int deleteIndex = Integer.parseInt(parsedInput[1]) - 1;
        if (deleteIndex > dukeTaskList.size()) {
            throw new DukeMissingTaskException();
        }
        Task deletedTask = dukeTaskList.get(deleteIndex);
        dukeTaskList.remove(deleteIndex);
        return dukeResponse.deleteTask(deletedTask, dukeTaskList.size());
    }

    /**
     * Handle find command.
     *
     * @param parsedInput the parsed input.
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleFind(String[] parsedInput) throws DukeException {
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
        return dukeResponse.listTasks(dukeSearchTaskList, true);
    }

    /**
     * Handle done command.
     *
     * @param parsedInput the parsed input
     * @return the response string from Duke.
     * @throws DukeException the caught duke exception
     */
    public String handleDone(String[] parsedInput) throws DukeException {
        if (parsedInput.length < 2) {
            throw new DukeMissingArgumentsException();
        }
        int index = Integer.parseInt(parsedInput[1]) - 1;
        if (index >= dukeTaskList.size()) {
            throw new DukeMissingTaskException();
        }
        Task currTask = dukeTaskList.get(index);
        currTask.markAsDone();
        return dukeResponse.markAsDone(currTask);
    }


}
