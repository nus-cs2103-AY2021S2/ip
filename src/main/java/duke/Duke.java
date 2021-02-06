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
    
    protected String chooseAction(String[] parsedInput, String originalInput, String response) throws UnknownInputException{
        
        switch (parsedInput[0]) {
        case "todo":
            response += ui.addPrint();
            ToDoTask todo = tasks.handleToDoTask(originalInput);
            response += ui.printTask(todo);
            response += ui.countTasks(tasks);
        
            break;
    
        case "deadline":
            response += ui.addPrint();
        
            DeadlineTask deadlineTask = tasks.handleDeadlineTask(originalInput);
        
            response += ui.printTask(deadlineTask);
            response += ui.countTasks(tasks);
        
            break;
    
        case "event":
        
            response += ui.addPrint();
        
            EventTask eventTask = tasks.handleEventTask(originalInput);
        
            response += ui.printTask(eventTask);
            response += ui.countTasks(tasks);
        
            break;
    
        case "list":
            response += ui.printStored(tasks);
        
            break;
    
        case "done":
            int number = Integer.valueOf(parsedInput[1]);
        
            response += ui.printMarked();
        
            Task completed = tasks.handleDone(number);
        
            response += ui.printTask(completed);
        
            break;
    
        case "check":
        
            String result = tasks.findOnDateTasks((parsedInput[1]));
        
            response += ui.print(result);
            break;
    
        case "bye":
            response += Ui.getByeMessage();
            break;
    
        case "delete":
            int index = Integer.valueOf(parsedInput[1]);
        
            response += ui.printRemoved();
        
            Task task = tasks.handleDelete(index);
        
            response += ui.printTask(task);
            response += ui.countTasks(tasks);
        
            break;
    
        case "find":
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
