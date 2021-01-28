package duke;

import duke.Exceptions.DukeExceptions;
import duke.Exceptions.MissingInputException;
import duke.Exceptions.UnclearInputException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

public class Duke {

    /** Storage instance to read and write file */
    private Storage storage;

    /** TaskList instance to store and handle tasks */
    private TaskList tasks;

    /** Ui instance to interact with user */
    private Ui ui;

    public Duke(String path) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(path, tasks);
    }

    /** Runs the program and handles each command from user */
    public void run() {
        ui.greetUser();
        Parser commandparser = new Parser();
        boolean readOn = true;
        while (readOn) {
            try {
                String command = ui.getCommand();
                commandparser.parse(command);
                String[] desc = commandparser.getDesc(command);
                String action = desc[0];
                switch (action) {
                case "bye":
                        readOn = false;
                        break;
                case "list":
                        ui.printList(tasks.taskList);
                        break;
                case "done":
                        int n = Integer.parseInt(desc[1]);
                        Task t = tasks.getTask(n);
                        tasks.markComplete(n);
                        ui.checkedTask(t);
                        break;
                case "todo":
                        Task todo = new Todo(desc[1]);
                        tasks.storeTask(todo);
                        ui.addedTask(tasks.taskList, todo);
                        break;
                case "event":
                        ui.requestDate();
                        String date = ui.getDate();
                        Task event = new Event(desc[1], date);
                        tasks.storeTask(event);
                        ui.addedTask(tasks.taskList, event);
                        break;
                case "deadline":
                        ui.requestDeadline();
                        String due = ui.getDate();
                        Task deadline = new Deadline(desc[1], due);
                        tasks.storeTask(deadline);
                        ui.addedTask(tasks.taskList, deadline);
                        break;
                case "delete":
                        Task task = tasks.getTask(Integer.parseInt(desc[1]));
                        tasks.deleteTask(Integer.parseInt(desc[1]));
                        ui.deletedTask(tasks.taskList, task);
                        break;
                case "find":
                        tasks.findTasks(desc[1]);
                        break;
                default:
                        throw new UnclearInputException();
                }
            } catch (MissingInputException e) {
                System.out.println((e.getMessage()));
            } catch (UnclearInputException e) {
                System.out.println((e.getMessage()));
            } catch (DukeExceptions e) {
                System.out.println((e.getMessage()));
            }
        }
        ui.sayGoodbye();
        storage.write(tasks);
    }
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}

