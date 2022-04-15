package duchess;

import duchess.Exceptions.DuchessExceptions;
import duchess.Exceptions.UnclearInputException;
import duchess.Tasks.Task;

public class Duchess {

    /** TaskList instance to store and handle tasks */
    private final TaskList tasks;

    /** Storage instance to read and write file */
    private final Storage storage;

    /** Ui instance to interact with user */
    private final Ui ui;

    /** Constructs a new Duke object
     *
     * @param path of file
     */
    public Duchess(String path) {
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
                Task t = tasks.completeTask(n, tasks);
                speech += ui.printChecked(t);
                break;
            case "todo":
                Task todo = tasks.addTodo(desc, tasks);
                speech += ui.printAdded(tasks.getTaskList(), todo);
                break;
            case "event":
                String date = commandParser.getDate(input);
                Task event = tasks.addEvent(desc, date, tasks);
                speech += ui.printAdded(tasks.getTaskList(), event);
                break;
            case "deadline":
                String due = commandParser.getDate(input);
                Task deadline = tasks.addDeadline(desc, due, tasks);
                speech += ui.printAdded(tasks.getTaskList(), deadline);
                break;
            case "delete":
                int index = Integer.parseInt(desc);
                Task task = tasks.getTask(index);
                tasks.deleteTask(index);
                speech += ui.printDeleted(tasks.getTaskList(), task);
                break;
            case "find":
                speech += tasks.findTasks(desc);
                break;
            case "today":
                speech += tasks.getReminder(tasks);
                break;
            case "weekly":
                speech += tasks.getNextReminder(tasks);
                break;
            default:
                throw new UnclearInputException();
            }
        } catch (DuchessExceptions e) {
            return e.getMessage();
        }
        storage.write();
        return speech;
    }
}

