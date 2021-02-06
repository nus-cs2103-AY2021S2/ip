package duke;

import duke.gui.Main;

import duke.register.Parser;
import duke.register.Storage;

import duke.task.*;

import javafx.application.Application;

/**
 * The class where duke is initialized and then launch GUI
 */
public class Duke {
    private TaskList taskList;
    private TaskList noteList;

    private Storage storage;
    private Storage notesStorage;

    public Duke() {
        storage = new Storage("data/dukeGUI.txt");
        notesStorage = new Storage("data/dukeGUINotes.txt");
        try {
            taskList = storage.load();
            noteList = notesStorage.load();
        } catch (Exception e) {
            taskList = new TaskList();
            noteList = new TaskList();
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
            notesStorage.saveTask(noteList);
            result = "Goodbye for now.\nHope to see you soon!";
        } else if (TaskType.equals("list")) {
            result = taskList.getAllTasks();
        } else if (TaskType.equals("notes")) {
            result = noteList.getAllTasks();
        } else if (TaskType.equals("reminders") || TaskType.equals("dues")) {
            result = taskList.getReminders();
        } else if (p.getCommandLength() > 1) {
            result = processLongCommands(p, command);
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
     * Method created to shorten the process method
     *
     * @param p
     * @param command
     * @return Duke response
     */
    public String processLongCommands(Parser p, String command) {
        p.parse();
        String TaskType = p.getTaskType().toLowerCase();
        String result = "";
        if (TaskType.equals("done")) {
            result = taskList.markAsDone(Integer.parseInt(p.getIndex()));
        } else if (TaskType.equals("delete") || TaskType.equals("remove")) {
            result = taskList.DeleteTask(Integer.parseInt(p.getIndex()));
        } else if (TaskType.equals("delete-note") || TaskType.equals("remove-note")) {
            result = noteList.DeleteTask(Integer.parseInt(p.getIndex()));
        } else if (TaskType.equals("find") || TaskType.equals("search")) {
            result = taskList.findTask(p.getTaskName());
        } else if (TaskType.equals("find-note") || TaskType.equals("search-note")) {
            result = noteList.findTask(p.getTaskName());
        } else if (TaskType.equals("todo")) {
            result = taskList.addTask(new TodoTask(command));
        } else if (TaskType.equals("deadline")) {
            result = taskList.addTask(new DeadlineTask(command));
        } else if (TaskType.equals("event")) {
            result = taskList.addTask(new EventTask(command));
        } else if (TaskType.equals("add")) {
            result = noteList.addTask(new Notes(command));
        }
        return result;
    }

    /**
     * Returns Duke's response to the GUI
     *
     * @param input
     * @return response
     */
    public String getResponse(String input) {
        return processGUI(input);
    }

    /**
     * The main method launching Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
