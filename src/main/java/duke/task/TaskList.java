package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.AlphabetsInsteadOfNumberException;
import duke.exception.DukeException;
import duke.exception.EmptyDateTimeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.EmptyDeleteException;
import duke.exception.EmptyEventException;
import duke.exception.EmptyListDeletionException;
import duke.exception.EmptyToDoException;
import duke.storage.Storage;

/**
 * TaskList class which stores all the tasks user keyed in.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage = new Storage();
    private final String line = "------------------------------------------";

    /**
     * Creates TaskList which is essentially an ArrayList of Task.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Retrieves the information in the TaskList.
     *
     * @return taskList ArrayList of task inputs
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns number of items in taskList.
     *
     * @return taskList.size() Number of items in the taskList
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns item at a given index.
     *
     * @param i Index of item to be retrieved
     * @return string Details of item retrieved
     */
    public String getInd(int i) {
        ArrayList<Task> arr = this.getList();
        return arr.get(i).toString();
    }

    /**
     * Create new Todo task and add it to taskList.
     *
     * @param input Details of Todo task
     * @return reply from Duke as String
     * @throws DukeException If task has no details
     */
    public String addToDo(String input) throws DukeException { //when user keys in todo abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
            this.taskList.add(t);
            storage.saveData(this);

            return "Got it. I've added this task:\n"
                    + t + "\nNow you have " + this.taskList.size()
                    + " tasks in the list.";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyToDoException();
        }

    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addToDo(Task input) { //when loading fr data [T][ ] abc
        this.taskList.add(input);
        storage.saveData(this);

    }

    /**
     * Create new Deadline task and add it to taskList.
     *
     * @param input Details of Deadline task
     * @return reply from Duke as String
     * @throws DukeException When user keys in invalid input
     * @throws ArrayIndexOutOfBoundsException If task has no details
     * @throws DateTimeParseException If task has no date or time
     */
    public String addDeadline(String input) throws DukeException { //when user keys in deadline abc
        try {
            String temp = input.split(" ", 2) [1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeadlineException();
        }
        String[] temp = input.split(" ", 2);
        String data = temp[1];
        String description = data.split(" /by ", 2)[0];
        String by = data.split(" /by ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
            Task t = new Deadline(description, dateTime);
            this.taskList.add(t);
            storage.saveData(this);

            return "Got it. I've added this task:\n"
                    + t + "\nNow you have " + this.taskList.size()
                    + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new EmptyDateTimeException();
        }

    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addDeadline(Task input) { //when loading fr data [D][ ] abc
        this.taskList.add(input);
        storage.saveData(this);
    }

    /**
     * Create new Event task and add it to taskList.
     *
     * @param input Details of Event task
     * @return reply from Duke as String
     * @throws DukeException When user keys in invalid input
     * @throws ArrayIndexOutOfBoundsException If task has no details
     * @throws DateTimeParseException If task has no date or time
     */
    public String addEvent(String input) throws DukeException { ////when user keys in event abc
        try {
            String temp = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyEventException();
        }
        String[] temp = input.split(" ", 2);
        String data = temp[1];
        String description = data.split(" /at ", 2)[0];
        String at = data.split(" /at ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
            Task t = new Event(description, dateTime);
            storage.saveData(this);

            this.taskList.add(t);
            return "Got it. I've added this task:\n"
                    + t + "\nNow you have " + this.taskList.size()
                    + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new EmptyDateTimeException();
        }
    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addEvent(Task input) { //when loading fr data [E][ ] abc
        this.taskList.add(input);
        storage.saveData(this);

    }

    /**
     * Mark an item as done in taskList.
     *
     * @return reply from Duke as String
     */
    public String markDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        this.taskList.get(index).setDone();
        storage.saveData(this);

        return "Nice! I've marked this task as done:\n"
                + this.taskList.get(index);
    }

    /**
     * Deletes given task from taskList.
     *
     * @param input Task to be deleted
     * @return reply from Duke as String
     * @throws DukeException in case input is invalid
     * @throws ArrayIndexOutOfBoundsException If event has no details
     * @throws NumberFormatException If alphabets were given instead of numbers
     * @throws IndexOutOfBoundsException If task has nothing to be deleted
     */
    public String delete(String input) throws DukeException {
        try {
            String temp = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeleteException();
        }

        try {
            String[] temp = input.split(" ", 2);
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (NumberFormatException e) {
            throw new AlphabetsInsteadOfNumberException();
        }

        try {
            String[] temp = input.split(" ", 2);
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyListDeletionException();
        }
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]) - 1;
        this.taskList.remove(index);
        storage.saveData(this);

        return "Noted. I've removed this task:\n"
                + this.taskList.get(index) + "\nNow you have "
                + this.taskList.size() + " tasks in the list.";
    }

    /**
     * List all items in taskList.
     *
     * @return reply from Duke as String
     */
    public String list() {
        String toPrint = "Here are the tasks in your list:\n";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            toPrint += i + "." + this.taskList.get(i - 1) + "\n";
        }
        return toPrint;
    }

    /**
     * Finds and lists all relevant items in taskList.
     *
     * @param input input of the item to be found
     * @return reply from Duke as String
     * @throws DukeException in case input is invalid
     * @throws ArrayIndexOutOfBoundsException If user does not input what to find
     */
    public String find(String input) throws DukeException {
        try {
            String temp = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeleteException();
        }
        String item = input.split(" ", 2)[1];
        String toPrint = "Here are the matching tasks in your list:\n";
        int counter = 1;
        ArrayList<Task> list = this.taskList;
        for (int i = 0; i < this.taskList.size(); i++) {
            String deets = list.get(i).getTaskDetails().split("]" + " ", 2)[1];
            if (deets.contains(item)) {
                toPrint += counter + "." + list.get(i) + "\n";
                counter++;
            }
        }
        return toPrint;
    }
}
