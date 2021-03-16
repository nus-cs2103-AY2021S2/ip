package duke;

import duke.gui.Main;
import duke.register.Parser;
import duke.register.Storage;
import duke.task.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The class where duke is initialized and then launch GUI
 */
public class Duke {
    private final Storage storage;
    private final Storage notesStorage;
    private TaskList taskList;
    private TaskList noteList;

    public Duke() {
        storage = new Storage("DukeTasks.txt");
        notesStorage = new Storage("DukeNotes.txt");
        try {
            taskList = storage.load();
            noteList = notesStorage.load();
        } catch (Exception e) {
            taskList = new TaskList();
            noteList = new TaskList();
        }
    }

    /**
     * The main method launching Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
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
        } else if (TaskType.equals("save")) {
            storage.saveTask(taskList);
            notesStorage.saveTask(noteList);
            result = "Your entries have been saved :)";
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
        storage.saveTask(taskList);
        notesStorage.saveTask(noteList);
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
            result = validIndex(p.getIndex())
                    ? taskList.markAsDone(Integer.parseInt(p.getIndex()))
                    : "Invalid Index!";
        } else if (TaskType.equals("delete") || TaskType.equals("remove")) {
            result = validIndex(p.getIndex())
                    ? taskList.DeleteTask(Integer.parseInt(p.getIndex()))
                    : "Invalid Index!";
        } else if (TaskType.equals("delete-note") || TaskType.equals("remove-note")) {
            result = validIndex(p.getIndex())
                    ? noteList.DeleteTask(Integer.parseInt(p.getIndex()))
                    : "Invalid Index!";
        } else if (TaskType.equals("find") || TaskType.equals("search")) {
            result = taskList.findTask(p.getTaskName());
        } else if (TaskType.equals("find-note") || TaskType.equals("search-note")) {
            result = noteList.findTask(p.getTaskWithoutType());
        } else if (TaskType.equals("todo")) {
            result = taskList.addTask(new TodoTask(command));
        } else if (TaskType.equals("deadline")) {
            result = getDeadline(command);
        } else if (TaskType.equals("event")) {
            result = getEvent(command);
        } else if (TaskType.equals("add")) {
            result = noteList.addTask(new Notes(command));
        } else {
            result = "Oops!!! Invalid Input :(";
        }
        return result;
    }

    /**
     * This method ensures that deadline is input in the correct format
     *
     * @param command
     * @return
     */
    public String getDeadline(String command) {
        String result = "";
        String[] divide = command.split("/");
        if (divide.length == 1 || divide[0].split(" ").length == 1) {
            result = "Oops!!! Incomplete command :(";
        } else if (divide[1].split(" ").length == 1
                || divide[1].split(" ").length > 3) {
            result = "Oops!!! Invalid Input :(";
        } else if (!isDate(divide[1].split(" ")[1])
                || (divide[1].split(" ").length > 2
                && !isTime(divide[1].split(" ")[2]))) {
            result = "Oops!!! Invalid Date/Time :(";
        } else {
            result = taskList.addTask(new DeadlineTask(command));
        }
        return result;
    }

    /**
     * This method ensures that the event is input in the correct format
     *
     * @param command
     * @return
     */
    public String getEvent(String command) {
        String result = "";
        String[] divide = command.split("/");
        if (divide.length == 1 || divide[0].split(" ").length == 1) {
            result = "Oops!!! Incomplete command :(";
        } else if (divide[1].split(" ").length == 1
                || divide[1].split(" ").length > 3) {
            result = "Oops!!! Invalid Input :(";
        } else if (!isDate(divide[1].split(" ")[1])
                || (divide[1].split(" ").length > 2
                && !isTime(divide[1].split(" ")[2]))) {
            result = "Oops!!! Invalid Date/Time :(";
        } else {
            result = taskList.addTask(new EventTask(command));
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
     * Checks if date format is correct
     *
     * @param date
     * @return
     */
    public boolean isDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if time format is correct
     *
     * @param time
     * @return
     */
    public boolean isTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if the string is a valid index
     *
     * @param input
     * @return
     */
    public boolean validIndex(String input) {
        try {
            int index = Integer.parseInt(input);
            if(index <= 0){
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
