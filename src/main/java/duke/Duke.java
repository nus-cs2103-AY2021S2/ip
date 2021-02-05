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
                printMessage(taskList.printTasks());
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

    public String processGUI(String input){
        String command = input;
        Parser p = new Parser(command);
        p.parse();
        String TaskType = p.getTaskType();
        String result = "";
        if (command.equals("bye") || command.equals("exit")) {
            ui.goodbye();
            storage.saveTask(taskList);
            result = "Goodbye for now.\nHope to see you soon!";
        } else if (command.equals("list")) {
            result = taskList.printTasks();
        } else if (p.getCommandLength() > 1) {
            if (TaskType.equals("done")) {
                result = taskList.markAsDone(Integer.parseInt(p.getIndex()));
            } else if (TaskType.equals("delete") || TaskType.equals("remove")) {
                result = taskList.DeleteTask(Integer.parseInt(p.getIndex()));
            } else if (TaskType.equals("find") || TaskType.equals("search")) {
                result = taskList.findTask(p.getTaskName());
            } else if (TaskType.equals("todo")) {
                result = taskList.addTask(new TodoTask(command));
            } else if (TaskType.equals("deadline")) {
                result = taskList.addTask(new DeadlineTask(command));
            } else if (TaskType.equals("event")) {
                result = taskList.addTask(new EventTask(command));
            }
        } else {
            if (TaskType.equals("todo") || TaskType.equals("deadline")
                    || TaskType.equals("event")) {
                result = "Oops!!! Incomplete command :(";
            //    throw new DukeException("Oops!!! Incomplete command :(");
            } else {
                result = "Oops!!! Invalid Input :(";
            //    throw new DukeException("Oops!!! Invalid Input :(");
            }
        }
        return result;
    }

    public String getResponse(String input) {
        return processGUI(input);
    }

    public void printMessage(String out){
        System.out.println(out);
    }

    //Main method where duke is initialized
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("data/duke.txt");
        duke.process();
    }
}
