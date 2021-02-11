package simulator;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import exception.DukeException;

import task.*;

import ui.Ui;

/**
 * ChatBot class that contains the function of the chat bot.
 */
public class ChatBot {
    private TaskList tasklist;
    private final Storage storage;
    private final Parser parser;

    /**
     * Construct a ChatBot object.
     */
    public ChatBot() {
        storage = new Storage();
        parser = new Parser();
        tasklist = new TaskList();
    }

    /**
     * Startup the chat bot by loading the task list from storage.
     * @return null
     */
    public String startup() {
        tasklist = storage.load(tasklist);
        assert tasklist.size() >= 0 : "size smaller than 0";
        return Ui.retrieveList(tasklist.size() == 0);
    }

    /**
     * Saves the current task list into the storage.
     * @return Save message
     * @throws IOException IOException
     */
    public String save() throws IOException {
       return storage.save(tasklist);
    }

    /**
     * Process the specified user <code>input</code>.
     * @param input input from user
     * @return A String from ChatBot
     */
    public String process(String input) {
        ArrayList<String> parsedInput = parser.parseInput(input);
        try {
            String command = parsedInput.get(0);
            if (command.equals("list")) {
                return Ui.printList(tasklist);
            }
            if (command.equals("done") || command.equals("delete")) {
                int index = Integer.parseInt(parsedInput.get(1));
                return command.equals("done") ? tasklist.completeTask(index) : tasklist.deleteTask(index);
            } else {
                String description = parsedInput.get(1);
                String duration;
                switch (command) { case "find":
                    return tasklist.find(description);
                case "todo":
                    if (detectAnomalies(command, description, null)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Todo(description));
                case "deadline":
                    duration = parsedInput.get(2);
                    if (detectAnomalies(command, description, duration)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Deadline(description, duration));
                case "event":
                    duration = parsedInput.get(2);
                    if (detectAnomalies(command, description, duration)) {
                        return Ui.TASK_CLASH_MSG;
                    }
                    return tasklist.addTask(new Event(description, duration));
                case "bye" :
                    return this.save();
                default:
                    throw new DukeException("☹ OOPS!!! Incorrect input, please check!");
                }
            }
        } catch (DukeException err) {
            return err.getMessage();
        } catch (Exception err) {
            return "☹ OOPS!!! Incorrect input, please check!";
        }
    }

    /**
     * Detects whether the added task clashes with another task in the list by verifying the specified task
     * <code>type</code>, <code>description</code> and <code> duration</code>.
     * @param type type of task
     * @param description description of task
     * @param duration duration of task
     * @return true if clash is detected, otherwise false.
     */
    public boolean detectAnomalies(String type, String description, String duration) {
        if (type.equals("todo")) {
            return compareTodo(description);
        } else {
            String[] dateAndTime = duration.split("\\s+");
            if (type.equals("deadline")) {
                return compareDeadline(description, dateAndTime);
            }
            if (type.equals("event")){
                return compareEvent(description, dateAndTime);
            }
            return false;
        }
    }

    /**
     * Detects whether the added todo task of specified <code>description</code>
     * clashes with another todo task in the list.
     * @param description description of the task
     * @return true if another similar todo task is found in the task list, otherwise false.
     */
    public boolean compareTodo(String description) {
        for (int i = 0; i < tasklist.size(); i++) {
            Task task = tasklist.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean compareType = task.getType().equals("T");
            if (compareType && compareDetails) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects whether the added Deadline task of specified <code> description</code> and <code>dateAndTime</code>
     * clashes with another Deadline task in the list.
     * @param description description of task.
     * @param dateAndTime date and time of task.
     * @return true if another similar Deadline task is found in the task list, otherwise false.
     */
    public boolean compareDeadline(String description, String[] dateAndTime) {
        boolean isSame = false;
        for (int i = 0; i < tasklist.size() && !isSame; i++) {
            Task task = tasklist.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean isDeadline = task.getType().equals("D");
            String time, date;
            if (compareDetails && isDeadline) {
                time = ((Deadline) task).getTime();
                date = ((Deadline) task).getDate();
                try {
                    String inputDate = LocalDate.parse(dateAndTime[0])
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    String inputTime = LocalTime.parse(dateAndTime[1])
                            .format(DateTimeFormatter.ofPattern("hh:mm a"));
                    isSame = (inputTime.equals(time) && date.equals(inputDate));

                } catch (ArrayIndexOutOfBoundsException ex) {
                    isSame = (time == null);
                }
            }
        }
        return isSame;
    }

    /**
     * Detects whether the added Event task of specified <code> description</code> and <code>dateAndTime</code>
     * clashes with another Event task in the list.
     * @param description description of task.
     * @param dateAndTime date and time of task.
     * @return true if another similar Event task is found in the task list, otherwise false.
     */
    public boolean compareEvent(String description, String[] dateAndTime) {
        boolean isSame = false;
        for (int i = 0; i < tasklist.size() && !isSame; i++) {
            Task task = tasklist.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean isEvent = task.getType().equals("E");
            String time, date;
            if (compareDetails && isEvent) {
                time = ((Event) task).getTime();
                date = ((Event) task).getDate();
                try {
                    String inputDate = LocalDate.parse(dateAndTime[0])
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    String inputTime = LocalTime.parse(dateAndTime[1])
                            .format(DateTimeFormatter.ofPattern("hh:mm a"));
                    System.out.println("input date: " + inputDate + " input time: " + inputTime);
                    System.out.println(" date: " + date + "  time: " + time);

                    isSame = (inputTime.equals(time) && date.equals(inputDate));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    isSame = (time == null);
                }
            }
        }
        return isSame;
    }
}
