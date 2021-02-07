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
import duke.exception.EmptyFindException;
import duke.exception.EmptyListDeletionException;
import duke.exception.EmptyTodoException;
import duke.exception.EmptyUpdateException;
import duke.exception.WrongUpdateTypeException;
import duke.storage.Storage;

/**
 * TaskList class which stores all the tasks user keyed in.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage = new Storage();

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
        String[] inputDetails = input.split(" ", 2);
        try {
            Task todoTask = new Todo(inputDetails[1]);
            this.taskList.add(todoTask);
            storage.saveData(this);

            int numberOfItems = this.taskList.size();
            if (numberOfItems == 1) {
                return "Got it. I've added this task:\n"
                    + todoTask + "\nNow you have 1 task in the list.";
            } else {
                return "Got it. I've added this task:\n"
                    + todoTask + "\nNow you have " + this.taskList.size()
                    + " tasks in the list.";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTodoException();
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
            String inputDetails = input.split(" ", 2) [1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeadlineException();
        }
        String[] inputDetailsDateAndTimeAsArray = input.split(" ", 2);
        String inputDetailsDateAndTime = inputDetailsDateAndTimeAsArray[1];
        String description = inputDetailsDateAndTime.split(" /by ", 2)[0];
        String dateAndTime = inputDetailsDateAndTime.split(" /by ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
            Task deadlineTask = new Deadline(description, dateTime);
            this.taskList.add(deadlineTask);
            storage.saveData(this);

            int numberOfItems = this.taskList.size();
            if (numberOfItems == 1) {
                return "Got it. I've added this task:\n"
                    + deadlineTask + "\nNow you have 1 task in the list.";
            } else {
                return "Got it. I've added this task:\n"
                    + deadlineTask + "\nNow you have " + this.taskList.size()
                    + " tasks in the list.";
            }
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
            String inputDetails = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyEventException();
        }
        String[] inputDetailsDateAndTimeAsArray = input.split(" ", 2);
        String inputDetailsDateAndTime = inputDetailsDateAndTimeAsArray[1];
        String description = inputDetailsDateAndTime.split(" /at ", 2)[0];
        String dateAndTime = inputDetailsDateAndTime.split(" /at ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
            Task eventTask = new Event(description, dateTime);
            this.taskList.add(eventTask);
            storage.saveData(this);

            int numberOfItems = this.taskList.size();
            if (numberOfItems == 1) {
                return "Got it. I've added this task:\n"
                        + eventTask + "\nNow you have 1 task in the list.";
            } else {
                return "Got it. I've added this task:\n"
                        + eventTask + "\nNow you have " + this.taskList.size()
                        + " tasks in the list.";
            }
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
            String taskNumber = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeleteException();
        }

        try {
            String[] inputDetails = input.split(" ", 2);
            int taskIndex = Integer.parseInt(inputDetails[1]) - 1;
            this.taskList.get(taskIndex);
        } catch (NumberFormatException e) {
            throw new AlphabetsInsteadOfNumberException();
        }

        try {
            String[] inputDetails = input.split(" ", 2);
            int taskIndex = Integer.parseInt(inputDetails[1]) - 1;
            this.taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyListDeletionException();
        }
        String[] inputDetails = input.split(" ", 2);
        int taskIndex = Integer.parseInt(inputDetails[1]) - 1;
        Task taskToBeDeleted = taskList.get(taskIndex);
        this.taskList.remove(taskIndex);
        storage.saveData(this);

        int numberOfItems = this.taskList.size();
        if (numberOfItems == 1) {
            return "Noted. I've removed this task:\n"
                + taskToBeDeleted + "\nNow you have 1 task in the list.";
        } else {
            return "Noted. I've removed this task:\n"
                + taskToBeDeleted + "\nNow you have "
                + this.taskList.size() + " tasks in the list.";
        }
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
            String taskNumber = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFindException();
        }
        String taskDetails = input.split(" ", 2)[1];
        String toPrint = "Here are the matching tasks in your list:\n";
        int counter = 1;
        ArrayList<Task> list = this.taskList;
        for (int i = 0; i < this.taskList.size(); i++) {
            String details = list.get(i).getTaskDetails().split("]" + " ", 2)[1];
            if (details.contains(taskDetails)) {
                toPrint += counter + "." + list.get(i) + "\n";
                counter++;
            }
        }
        return toPrint;
    }

    /**
     * Method to update an event in the list.
     *
     * @param input of user with details to be ammended
     * @return confirmation message that the update was successful
     * @throws DukeException in case of wrong type or insufficient information
     */
    public String updateEvent(String input) throws DukeException {
        try {
            String taskNumber = input.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeleteException();
        }

        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new EmptyUpdateException();
        }

        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        int taskIndex = taskNumber - 1;
        String taskType = this.taskList.get(taskIndex).getTaskType();
        try {
            String newDetails = input.split("/to ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongUpdateTypeException();
        }
        String newDetails = input.split("/to ", 2)[1];

        if (taskType.equals("todo")) {
            if (newDetails.contains("/by")) {
                throw new WrongUpdateTypeException();
            } else if (newDetails.contains("/at")) {
                throw new WrongUpdateTypeException();
            } else {
                this.taskList.get(taskIndex).updateTaskDetails(newDetails);
                Task newTask = this.taskList.get(taskIndex);
                storage.saveData(this);

                return "Noted. I've updated this task:\n"
                        + taskNumber + "." + newTask;
            }
        } else if (taskType.equals("deadline")) {
            try {
                String deadlineDetails = input.split("/by ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyUpdateException();
            }

            if (newDetails.contains("/at")) {
                throw new WrongUpdateTypeException();
            } else {
                String description = newDetails.split(" /by ", 2)[0];
                String dateAndTime = newDetails.split(" /by ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
                    this.taskList.get(taskIndex).updateTaskDetails(description);
                    this.taskList.get(taskIndex).updateTaskDateAndTime(dateTime);
                    Task newTask = this.taskList.get(taskIndex);
                    storage.saveData(this);

                    return "Noted. I've updated this task:\n"
                            + taskNumber + "." + newTask;

                } catch (DateTimeParseException e) {
                    throw new EmptyDateTimeException();
                }
            }
        } else if (taskType.equals("event")) {
            try {
                String eventDetails = input.split("/at ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyUpdateException();
            }

            if (newDetails.contains("/by")) {
                throw new WrongUpdateTypeException();
            } else {
                String description = newDetails.split(" /at ", 2)[0];
                String dateAndTime = newDetails.split(" /at ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
                    this.taskList.get(taskIndex).updateTaskDetails(description);
                    this.taskList.get(taskIndex).updateTaskDateAndTime(dateTime);
                    Task newTask = this.taskList.get(taskIndex);
                    storage.saveData(this);

                    return "Noted. I've updated this task:\n"
                            + taskNumber + "." + newTask;

                } catch (DateTimeParseException e) {
                    throw new EmptyDateTimeException();
                }
            }
        } else {
            assert false : "All cases should be handled by here.";
            return "";
        }
    }
}
