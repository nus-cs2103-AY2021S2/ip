package duke.task;

import duke.exception.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * TaskList class which stores all the tasks user keyed in.
 */
public class TaskList {
    private ArrayList<Task> taskList;

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

    String line = "------------------------------------------";

    /**
     * Create new Todo task and add it to taskList.
     *
     * @param input Details of Todo task
     * @throws ArrayIndexOutOfBoundsException If task has no details
     */
    public void addToDo(String input) { //when user keys in todo abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyToDoException();
            return;
        }
        Task t = new Todo(temp[1]);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addToDo(Task input) { //when loading fr data [T][ ] abc
        this.taskList.add(input);
    }

    /**
     * Create new Deadline task and add it to taskList.
     *
     * @param input Details of Deadline task
     * @throws ArrayIndexOutOfBoundsException If task has no details
     * @throws DateTimeParseException If task has no date or time
     */
    public void addDeadline(String input) { //when user keys in deadline abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyDeadlineException();
            return;
        }
        String data = temp[1];
        String description = data.split(" /by ", 2)[0];
        String by = data.split(" /by ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

        } catch (DateTimeParseException e) {
            new EmptyDateTimeException();
            return;
        }
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Task t = new Deadline(description, dateTime);
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addDeadline(Task input) { //when loading fr data [D][ ] abc
        this.taskList.add(input);
    }

    /**
     * Create new Event task and add it to taskList.
     *
     * @param input Details of Event task
     * @throws ArrayIndexOutOfBoundsException If task has no details
     * @throws DateTimeParseException If task has no date or time
     */
    public void addEvent(String input) { ////when user keys in event abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyEventException();
            return;
        }
        String data = temp[1];
        String description = data.split(" /at ", 2)[0];
        String at = data.split(" /at ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(at, formatter);

        } catch (DateTimeParseException e) {
            new EmptyDateTimeException();
            return;
        }
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
        Task t = new Event(description, dateTime);
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Adds given task to taskList.
     *
     * @param input Task to be added
     */
    public void addEvent(Task input) { //when loading fr data [E][ ] abc
        this.taskList.add(input);
    }

    /**
     * Mark an item as done in taskList.
     */
    public void markDone(String input) {
        System.out.println(line);
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        System.out.println("Nice! I've marked this task as done:");
        this.taskList.get(index).setDone();
        System.out.println(this.taskList.get(index));
        System.out.println(line);
    }

    /**
     * Deletes given task from taskList.
     *
     * @param input Task to be deleted
     * @throws ArrayIndexOutOfBoundsException If event has no details
     * @throws NumberFormatException If alphabets were given instead of numbers
     * @throws IndexOutOfBoundsException If task has nothing to be deleted
     */
    public void delete(String input) {
        String[] temp = input.split(" ", 2);

        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyDeleteException();
            return;
        }

        try {
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (NumberFormatException e) {
            new AlphabetsInsteadOfNumberException();
            return;
        }

        try {
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (IndexOutOfBoundsException e) {
            new EmptyListDeletionException();
            return;
        }

        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(temp[1]) - 1;
        System.out.println(this.taskList.get(index));
        this.taskList.remove(index);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * List all items in taskList.
     */
    public void list() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            System.out.println(i + "." + this.taskList.get(i - 1));
        }
        System.out.println(line);
    }

    /**
     * Finds and lists all relevant items in taskList.
     *
     * @param input input of the item to be found
     * @throws ArrayIndexOutOfBoundsException If user does not input what to find
     */
    public void find(String input) {
        try {
            String[] arr = input.split(" ", 2);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyDeleteException();
            return;
        }
        String item = input.split(" ", 2)[1];
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        int counter = 1;
        ArrayList<Task> list = this.taskList;
        for (int i = 0; i < this.taskList.size(); i++) {
            String deets = list.get(i).getTaskDetails().split("]" + " ", 2)[1];
            if (deets.contains(item)) {
                System.out.println(counter + "." + list.get(i));
                counter++;
            }
        }
        System.out.println(line);
    }
}
