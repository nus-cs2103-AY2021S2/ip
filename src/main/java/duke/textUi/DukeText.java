package duke.textUi;

import duke.exception.DukeException;
import duke.register.Parser;
import duke.register.Storage;
import duke.register.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * The main class where the duke text ui is run
 * This is slightly outdated from the current versions
 * Note features not available
 */
public class DukeText {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    public DukeText(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
        ui.greetings();
    }

    //Uses the UI and runs against various conditions
    public void process() throws DukeException {
        String command;
        while (true) {
            command = ui.getCommand();
            Parser p = new Parser(command);
            p.parse();
            String TaskType = p.getTaskType();
            if (command.equals("bye") || command.equals("exit")) {
                ui.goodbye();
                storage.saveTask(taskList);
                break;
            } else if (command.equals("list")) {
                printMessage(taskList.getAllTasks());
            } else if (p.getCommandLength() > 1) {
                if (TaskType.equals("done")) {
                    printMessage(taskList.markAsDone(Integer.parseInt(p.getIndex())));
                } else if (TaskType.equals("delete") || TaskType.equals("remove")) {
                    printMessage(taskList.DeleteTask(Integer.parseInt(p.getIndex())));
                } else if (TaskType.equals("find") || TaskType.equals("search")) {
                    printMessage(taskList.findTask(p.getTaskName()));
                } else if (TaskType.equals("todo")) {
                    printMessage(taskList.addTask(new TodoTask(command)));
                } else if (TaskType.equals("deadline")) {
                    printMessage(taskList.addTask(new DeadlineTask(command)));
                } else if (TaskType.equals("event")) {
                    printMessage(taskList.addTask(new EventTask(command)));
                }
            } else {
                if (TaskType.equals("todo") || TaskType.equals("deadline")
                        || TaskType.equals("event")) {
                    throw new DukeException("Oops!!! Incomplete command :(");
                } else {
                    throw new DukeException("Oops!!! Invalid Input :(");
                }
            }
        }
    }

    public void printMessage(String out) {
        System.out.println(out);
    }

    //Main method where duke is initialized
    public static void main(String[] args) throws DukeException {
        DukeText duke = new DukeText("data/duke.txt");
        duke.process();
    }
}
