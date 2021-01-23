package duke;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;

import duke.tasks.ToDoTask;
import duke.tasks.Task;
import duke.tasks.EventTask;
import duke.tasks.DeadlineTask;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {

        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printDivider();
        ui.welcome();
        ui.printDivider();

        try {
            boolean carryOn = true;

            while (carryOn) {

                String action = ui.read();
                Parser parser = new Parser(action);
                parser.check();
                String[] parsedAction = parser.getParsedAction();

                switch (parsedAction[0]) {
                    case "todo":
                        ui.printDivider();
                        ui.addPrint();

                        ToDoTask todo = tasks.handleToDoTask(action);

                        ui.printTask(todo);
                        ui.countTasks(tasks);
                        ui.printDivider();
                        break;

                    case "deadline":
                        ui.printDivider();
                        ui.addPrint();

                        DeadlineTask deadlineTask = tasks.handleDeadlineTask(action);

                        ui.printTask(deadlineTask);
                        ui.countTasks(tasks);
                        ui.printDivider();
                        break;

                    case "event":
                        ui.printDivider();

                        ui.addPrint();

                        EventTask eventTask = tasks.handleEventTask(action);

                        ui.printTask(eventTask);
                        ui.countTasks(tasks);
                        ui.printDivider();
                        break;

                    case "list":
                        ui.printDivider();
                        ui.printStored(tasks);
                        ui.printDivider();
                        break;

                    case "done":
                        int number = Integer.valueOf(parsedAction[1]);
                        ui.printDivider();
                        ui.printMarked();

                        Task completed = tasks.handleDone(number);

                        ui.printTask(completed);
                        ui.printDivider();
                        break;

                    case "check":
                        ui.printDivider();
                        String result = tasks.findOnDateTasks((parsedAction[1]));
                        ui.print(result);
                        ui.printDivider();
                        break;

                    case "bye":
                        carryOn = false;
                        break;

                    case "delete":
                        int index = Integer.valueOf(parsedAction[1]);
                        ui.printDivider();
                        ui.printRemoved();

                        Task task = tasks.handleDelete(index);
                        ui.printTask(task);
                        ui.countTasks(tasks);

                        ui.printDivider();
                        break;

                    default:
                        throw new UnknownInputException();
                }
            }

            ui.printDivider();
            ui.bye();
            ui.printDivider();

            storage.write(tasks);
        } catch (DukeException e) {
            ui.printDivider();
            ui.print(e.getMessage());
            ui.printDivider();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
