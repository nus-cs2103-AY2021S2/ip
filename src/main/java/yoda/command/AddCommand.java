package yoda.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yoda.storage.Storage;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.task.ToDo;
import yoda.ui.Ui;

/**
 * AddCommand class that handles adding tasks to TaskList and a child of Command class.
 */
public class AddCommand extends Command {
    /**
     * Creates an AddCommand object.
     * @param details Details of AddCommand object.
     */
    public AddCommand(String[] details) {
        super(details);
        commandType = CommandType.valueOf(details[0]);
    }

    /**
     * Makes the task requested by user.
     * @return Task requested by the user.
     * @throws InvalidCommandFormatException If invalid arguments are entered for command.
     * @throws InvalidDateTimeFormatException If datetime entered is of an invalid format.
     */
    public Task makeTask() throws InvalidCommandFormatException, InvalidDateTimeFormatException {
        switch(commandType) {
        case TODO:
            return makeToDo(details);
        case EVENT:
            return makeEvent(details);
        case DEADLINE:
            return makeDeadline(details);
        default:
            return new ToDo("This task should not have appeared!");
        }
    }

    /**
     * Makes ToDo tasks using the arguments given by user.
     * @param toDoDesc Description of ToDo task to be created.
     * @return ToDo task requested by user.
     * @throws InvalidCommandFormatException If invalid arguments are given for ToDo task.
     */
    public ToDo makeToDo(String ... toDoDesc) throws InvalidCommandFormatException {
        if (toDoDesc.length == 1) {
            throw new InvalidCommandFormatException("Give a description for todo, you must!");
        }
        StringBuilder toDoDetails = new StringBuilder();
        for (int i = 1; i < toDoDesc.length; i++) {
            toDoDetails.append(toDoDesc[i]).append(" ");
        }
        return new ToDo(toDoDetails.toString());
    }

    /**
     * Makes Event tasks using the arguments given by user.
     * @param eventDesc Description of Event task to be created.
     * @return Event task requested by user.
     * @throws InvalidCommandFormatException If invalid arguments are given for Event task.
     * @throws InvalidDateTimeFormatException If datetime entered for Event task is in an invalid format.
     */
    public Event makeEvent(String ... eventDesc) throws InvalidCommandFormatException,
            InvalidDateTimeFormatException {
        try {
            int i = 1;
            StringBuilder eventDetails = new StringBuilder();
            while (!eventDesc[i].equals("/at")) {
                eventDetails.append(eventDesc[i]).append(" ");
                i++;
            }
            if (!eventDesc[i].equals("/at")) {
                throw new InvalidCommandFormatException("Use /at when creating an event, you must!");
            }
            assert i > 1 : "i must be pointing at date time by this point";
            String eventDateAndTime = checkDateTime(eventDesc[i + 1], eventDesc[i + 2]);
            return new Event(eventDetails.toString(), eventDateAndTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("The right way to create an event,\n"
                    + "'event [description] /at YYYY-MM-DD HHmm' is!");
        }
    }

    /**
     * Makes Deadline tasks using the arguments given by user.
     * @param deadlineDesc Description of Deadline task to be created.
     * @return Deadline task requested by user.
     * @throws InvalidCommandFormatException If invalid arguments are given for Deadline task.
     * @throws InvalidDateTimeFormatException If datetime entered for Deadline task is in an invalid format.
     */
    public Deadline makeDeadline(String ... deadlineDesc) throws InvalidCommandFormatException,
            InvalidDateTimeFormatException {
        try {
            int i = 1;
            StringBuilder deadlineDetails = new StringBuilder();
            while (!deadlineDesc[i].equals("/by")) {
                deadlineDetails.append(deadlineDesc[i]).append(" ");
                i++;
            }
            if (!deadlineDesc[i].equals("/by")) {
                throw new InvalidCommandFormatException("Use /by when creating a deadline, you must!");
            }
            assert i > 1 : "i must be pointing at date time by this point";
            String deadlineDateAndTime = checkDateTime(deadlineDesc[i + 1], deadlineDesc[i + 2]);
            return new Deadline(deadlineDetails.toString(), deadlineDateAndTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("The right way to create a deadline,\n"
                    + "'deadline [description] /by YYYY-MM-DD HHmm' is!");
        }
    }

    /**
     * Checks if the datetime format is correct and returns a valid datetime String.
     * @param date Date of task
     * @param time Time of task
     * @return Valid datetime String for Event/Deadline task
     * @throws InvalidDateTimeFormatException If datetime format is invalid
     */
    public String checkDateTime(String date, String time) throws InvalidDateTimeFormatException {
        try {
            String dateTime = date + " " + time;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime taskTime = LocalDateTime.parse(dateTime, format);
            LocalDateTime currTime = LocalDateTime.now();
            if (taskTime.isBefore(currTime)) {
                throw new InvalidDateTimeFormatException("Not possible, time travel is!\n"
                        + "An appropriate date and time, you must input!");
            }
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("'YYYY-MM-DD HHmm', the right format is!\n"
                    + "2021-01-01 1600, an example is!");
        }
    }

    /**
     * Adds a specific type of task to the TaskList based on the command issued by user and
     * writes updated TaskList to the file.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message to user informing if the AddCommand was executed successfully or not.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = makeTask();
            taskList.addTask(task);
            storage.serialize(taskList);
            return ui.printAddedTask(task);
        } catch (InvalidCommandFormatException | InvalidDateTimeFormatException e) {
            return e.getMessage();
        }
    }
}
