package duke;

import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

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
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            response += ui.addPrint();
            ToDoTask todo = tasks.handleToDoTask(input);
            response += ui.printTask(todo);
            response += ui.countTasks(tasks);
            break;
        case "deadline":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            response += ui.addPrint();
            DeadlineTask deadlineTask = tasks.handleDeadlineTask(input);
            response += ui.printTask(deadlineTask);
            response += ui.countTasks(tasks);
            break;
        case "event":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            response += ui.addPrint();
            EventTask eventTask = tasks.handleEventTask(input);
            response += ui.printTask(eventTask);
            response += ui.countTasks(tasks);
            break;
        case "list":
            response += ui.printStored(tasks);
            break;
        case "done":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            int number = Integer.valueOf(parsedInput[1]);
            response += ui.printMarked();
            Task completed = tasks.handleDone(number);
            response += ui.printTask(completed);
            break;
        case "check":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            String result = tasks.findOnDateTasks((parsedInput[1]));
            response += ui.print(result);
            break;
        case "bye":
            response += Ui.getByeMessage();
            break;
        case "delete":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            int index = Integer.valueOf(parsedInput[1]);
            response += ui.printRemoved();
            Task task = tasks.handleDelete(index);
            response += ui.printTask(task);
            response += ui.countTasks(tasks);
            break;
        case "find":
            assert parsedInput.length > 1 : "Something went wrong with the parsing!";
            String keyword = parsedInput[1];
            response += ui.printMatching();
            List<Task> matches = tasks.getMatch(keyword);
            response += ui.printList(matches);
            break;
        default:
            throw new UnknownInputException();
        }
        return response;
    }
    /**
     * Function to make the response of Duke depending on the user input
     * @param input representing the user input
     * @returns String representing the response of Duke
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
