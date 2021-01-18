package duke;

import exception.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * A duke chat-bot object.
 */
public class Duke {
    /**
     * Duke stored task list.
     */
    public ArrayList<Task> taskList;

    /**
     * DukeResponse object to handle responses.
     */
    public DukeResponse dukeResponse;
    /**
     * Duke message dividers.
     */
    public final static String  DIV = "\n____________________________"
        + "________________________________\n";
    /**
     * Duke task headers.
     */
    public final static String TASK_HEADER = "Alrighty! I've added this task:\n";
    /**
     * Duke task footer1.
     */
    public final static String TASK_FOOTER1 = "\nNow you have ";
    /**
     * Duke task footer2.
     */
    public final static String TASK_FOOTER2 = " tasks in the list.";
    /**
     * Instantiates a new duke.
     */
    public Duke() {
        taskList = new ArrayList<>();
        dukeResponse = new DukeResponse();
    }

    /**
     * Parses given input and sets a new current message based on input.
     *
     * @param input the input message.
     * @return integer. 0 represents an terminating entry, 1 represents a continuing entry.
     */
    public int parse(String input) throws DukeMissingArgumentsException,
            DukeException, DukeMissingTaskException {
        String[] parsedInput = input.split("\\s+", 2);
        switch(parsedInput[0]) {
            case (""):
                throw new DukeNoInputException();
            case ("todo"): {
                if (parsedInput.length < 2 || parsedInput[1].length() == 0) {
                    throw new DukeMissingArgumentsException();
                } else {
                    Todo currTodo = new Todo(parsedInput[1]);
                    taskList.add(currTodo);
                    dukeResponse.addTask(currTodo, taskList.size());
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
                taskList.add(currEvent);
                dukeResponse.addTask(currEvent, taskList.size());
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
                taskList.add(currDeadline);
                dukeResponse.addTask(currDeadline, taskList.size());
                break;
            }
            case ("list"):
                dukeResponse.listTasks(taskList);
                break;
            case ("done"): {
                if (parsedInput.length < 2) {
                    throw new DukeMissingArgumentsException();
                }
                int index = Integer.parseInt(parsedInput[1])-1;
                if (index >= taskList.size()) {
                    throw new DukeMissingTaskException();
                }
                Task currTask = taskList.get(index);
                currTask.markAsDone();
                dukeResponse.markAsDone(currTask);
                break;
            }
            case ("bye"):
                dukeResponse.farewell();
                return 0;
            default:
                throw new DukeUnknownInputException();
        }
        return 1;
    }
}
