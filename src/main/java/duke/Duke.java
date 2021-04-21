package duke;

import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.Task;

/**
 * Duke class that simulates the running of the Duke Program
 */
public class Duke {

    /** Storage instance that is used by Duke during run for loading and writing of file*/
    private Storage storage;

    /** TaskList instance used by Duke during run that manages the tasks */
    private TaskList tasks;

    /** Ui instance used by Duke during run to interact with User */
    private Ui ui;

    private String filePath = "./data/tasks.txt";

    /**
     * Constructor for the Duke class
     */
    public Duke() {

        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFileIntoArrayList());
        } catch (DukeException e) {

            ui.showLoadingError();
            tasks = new TaskList();

        }
    }
    /**
     * Returns a String containing the tasks that have this date
     *
     * @param parsedInput the parsed input to decide the action
     * @param input the original Input to aid in the action to take
     * @param toReply the current String Duke is supposed to reply with
     * @return A string representing the response of Duke
     * @throws UnknownInputException when there the input or command is not known
     */
    protected String chooseAction(String[] parsedInput, String input, String toReply) throws UnknownInputException {
        String response = toReply;

        switch (parsedInput[0]) {

        case "todo":
            assert parsedInput.length > 1 : "Something went wrong with the parsing of todo!";

            response = handleAddTask(parsedInput[0], response, input);
            break;

        case "deadline":
            assert parsedInput.length > 1 : "Something went wrong with the parsing of deadline!";;

            response = handleAddTask(parsedInput[0], response, input);
            break;

        case "event":
            assert parsedInput.length > 1 : "Something went wrong with the parsing of event!";

            response = handleAddTask(parsedInput[0], response, input);
            break;

        case "list":

            response = handleList(response);
            break;

        case "done":
            assert parsedInput.length > 1 : "Something went wrong with the parsing done";

            response = handleDone(parsedInput, response);
            break;

        case "check":
            assert parsedInput.length > 1 : "Something went wrong with the parsing check!";

            response = handleCheck(parsedInput, response);
            break;

        case "bye":
            response += Ui.getByeMessage();
            break;

        case "delete":
            assert parsedInput.length > 1 : "Something went wrong with the parsing delete!";

            response = handleDelete(parsedInput, response);
            break;

        case "find":
            assert parsedInput.length > 1 : "Something went wrong with the parsing find!";

            response = handleFind(parsedInput, response);
            break;

        case "priority":
            assert parsedInput.length > 1 : "Something went wrong with the parsing priority!";

            response = handlePriority(parsedInput, response);
            break;

        default:
            throw new UnknownInputException();
        }

        return response;
    }

    /**
     * Runs the logic for the adding of tasks to the list
     * and builiding the response
     *
     * @param type the type of task to add
     * @param response the current response string of Duke
     * @param input the original Input to aid in the action to take
     * @return A string representing the response of Duke
     */
    private String handleAddTask(String type, String response, String input) {
        Task task = new Task();

        if (type.equals("todo")) {
            task = tasks.handleToDoTask(input);

        } else if (type.equals("deadline")) {
            task = tasks.handleDeadlineTask(input);

        } else if (type.equals("event")) {
            task = tasks.handleEventTask(input);
        }

        response += ui.addPrint();
        response += ui.printTask(task);
        response += ui.countTasks(tasks);

        return response;
    }

    /**
     * Runs the logic for the marking of task to be done and
     * building the response
     *
     * @param parsedInput the input that has been parsed by the parser object
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handleDone(String[] parsedInput, String response) {
        int number = Integer.valueOf(parsedInput[1]);

        response += ui.printMarked();

        Task completed = tasks.handleDone(number);

        response += ui.printTask(completed);

        return response;
    }

    /**
     * Runs the logic for the get the current list tasks
     * building the response
     *
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handleList (String response) {
        response += ui.printStored(tasks);
        return response;
    }

    /**
     * Runs the logic for the checking of tasks on a particular
     * date and building the response
     *
     * @param parsedInput the input that has been parsed by the parser object
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handleCheck (String[] parsedInput, String response) {
        String result = tasks.findOnDateTasks((parsedInput[1]));
        response += ui.print(result);

        return response;
    }

    /**
     * Runs the logic for the deletion of task
     * and building the response
     *
     * @param parsedInput the input that has been parsed by the parser object
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handleDelete (String[] parsedInput, String response) {
        int index = Integer.valueOf(parsedInput[1]);

        response += ui.printRemoved();

        Task task = tasks.handleDelete(index);

        response += ui.printTask(task);
        response += ui.countTasks(tasks);

        return response;
    }

    /**
     * Runs the logic for finding a task containing the keyword and
     * building the response
     *
     * @param parsedInput the input that has been parsed by the parser object
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handleFind (String[] parsedInput, String response) {
        String keyword = parsedInput[1];

        response += ui.printMatching();

        List<Task> matches = tasks.getMatch(keyword);

        response += ui.printList(matches);
        return response;
    }

    /**
     * Runs the logic for assigning a priority to a task and
     * building the response
     *
     * @param parsedInput the input that has been parsed by the parser object
     * @param response the current response string of Duke
     * @return A string representing the response of Duke
     */
    private String handlePriority (String[] parsedInput, String response) {
        int taskNum = Integer.valueOf(parsedInput[1]);
        String priority = parsedInput[2];

        response += ui.getSetPriorityMessage();

        Task taskChanged = tasks.handleSetPriority(taskNum, priority);

        response += ui.printTask(taskChanged);

        return response;
    }

    /**
     * Function to make the response of Duke depending on the user input
     * @param input representing the user input
     * @return String representing the response of Duke
     */
    protected String getResponse(String input) {
        String toReply = "";

        try {
            Parser parser = new Parser(input);
            parser.check();

            String[] parsedInput = parser.getParsedAction();
            toReply = chooseAction(parsedInput, input, toReply);

            storage.write(tasks);

        } catch (DukeException e) {
            return e.getMessage();
        }

        return toReply;
    }
}
