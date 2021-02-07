package duke;

import duke.Exceptions.DukeExceptions;
import duke.Exceptions.MissingInputException;
import duke.Exceptions.UnclearInputException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

public class Duke {

    /** TaskList instance to store and handle tasks */
    private TaskList tasks;

    /** Storage instance to read and write file */
    private Storage storage;

    /** Ui instance to interact with user */
    private Ui ui;

    /** Constructs a new Duke object
     *
     * @param path of file
     */
    public Duke(String path) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(path, tasks);
    }

    /** Runs the program and handles each command from user */
    public String getResponse(String input) {
        Parser commandParser = new Parser();
        String speech = "";
        try {
            commandParser.parse(input);
            String action = commandParser.getCommand(input);
            assert action.length() > 1 : "Action not retrieved accurately";
            String desc = commandParser.getDesc(input);
            switch (action) {
            case "bye":
                speech += ui.sayGoodbye();
                break;
            case "list":
                speech += ui.printList(tasks.getTaskList());
                break;
            case "done":
                int n = Integer.parseInt(desc);
                Task t = tasks.getTask(n);
                tasks.markComplete(n);
                speech += ui.printChecked(t);
                break;
            case "todo":
                Task todo = new Todo(desc);
                tasks.storeTask(todo);
                speech += ui.printAdded(tasks.getTaskList(), todo);
                break;
            case "event":
                String date = commandParser.getDate(input);
                System.out.println(date);
                Task event = new Event(desc, date);
                tasks.storeTask(event);
                speech += ui.printAdded(tasks.getTaskList(), event);
                break;
            case "deadline":
                String due = commandParser.getDate(input);
                Task deadline = new Deadline(desc, due);
                tasks.storeTask(deadline);
                speech += ui.printAdded(tasks.getTaskList(), deadline);
                break;
            case "delete":
                Task task = tasks.getTask(Integer.parseInt(desc));
                tasks.deleteTask(Integer.parseInt(desc));
                speech += ui.printDeleted(tasks.getTaskList(), task);
                break;
            case "find":
                speech += tasks.findTasks(desc);
                break;
            default:
                throw new UnclearInputException();
            }
        } catch (MissingInputException e) {
            return e.getMessage();
        } catch (UnclearInputException e) {
            return e.getMessage();
        } catch (DukeExceptions e) {
            return e.getMessage();
        }
        storage.write(tasks);
        return speech;
    }
}

