package duke;

import duke.exception.DukeException;
import duke.register.Storage;
import duke.register.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.register.Parser;

/**
 * The main class where the duke is run
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String path) {
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
                taskList.printTasks();
            } else if (p.getCommandLength() > 1) {
                if (TaskType.equals("done")) {
                    taskList.markAsDone(Integer.parseInt(p.getIndex()));
                } else if (TaskType.equals("delete") || TaskType.equals("remove")) {
                    taskList.DeleteTask(Integer.parseInt(p.getIndex()));
                } else if (TaskType.equals("find") || TaskType.equals("search")) {
                    taskList.findTask(p.getTaskName());
                } else if (TaskType.equals("todo")) {
                    taskList.addTask(new TodoTask(command));
                } else if (TaskType.equals("deadline")) {
                    taskList.addTask(new DeadlineTask(command));
                } else if (TaskType.equals("event")) {
                    taskList.addTask(new EventTask(command));
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

    //Main method where duke is initialized
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("data/duke.txt");
        duke.process();
    }
}
