package duke;

import exception.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles duke operations and parses input commands.
 */
public class Duke {
    /**
     * DukeResponse object to handle responses.
     */
    public DukeResponse dukeResponse;

    /**
     * DukeStorage object to handle history files.
     */
    public DukeStorage dukeStorage;
    /**
     * DukeTaskList to handle task list operations.
     */
    public DukeTaskList dukeTaskList;

    /**
     * Instantiates a new duke.
     */
    //TODO: handle file not found exception
    public Duke() throws IOException {
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
            Event currEvent = new Event(args[0], args[1]);
            dukeTaskList.add(currEvent);
            dukeResponse.addTask(currEvent, dukeTaskList.size());
            break;
        }
        case ("deadline"): {
            if (parsedInput.length < 2) {
                throw new DukeMissingArgumentsException();
            }
            String[] args = parsedInput[1].split("/by", 2);
            if (args.length < 2 || args[1].length() == 0) {
                throw new DukeMissingArgumentsException();
            }
            Deadline currDeadline = new Deadline(args[0], args[1]);
            dukeTaskList.add(currDeadline);
            dukeResponse.addTask(currDeadline, dukeTaskList.size());
            break;
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
            dukeResponse.listTasks(dukeTaskList);
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
