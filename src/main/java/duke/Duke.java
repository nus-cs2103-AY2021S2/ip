package duke;

import duke.gui.Main;

import duke.register.Parser;
import duke.register.Storage;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;

import javafx.application.Application;

/**
 * The class where duke is initialized and then launch GUI
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke(String path) {
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    /**
     * This method takes in the input from the GUI
     *
     * @param input
     * @return the result of the command
     */
    public String processGUI(String input) {
        String command = input;
        Parser p = new Parser(command);
        p.parse();
        String TaskType = p.getTaskType().toLowerCase();
        String result = "";
        if (TaskType.equals("bye") || TaskType.equals("exit")) {
            storage.saveTask(taskList);
            result = "Goodbye for now.\nHope to see you soon!";
        } else if (TaskType.equals("list")) {
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
            } else {
                result = "Oops!!! Invalid Input :(";
            }
        }
        return result;
    }

    /**
     * Returns Duke's response to the GUI
     * @param input
     * @return response
     */
    public String getResponse(String input) {
        return processGUI(input);
    }

    /**
     * The main method launching Duke
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
