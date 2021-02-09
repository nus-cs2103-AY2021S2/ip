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
 * AddCommand class that handles adding tasks to TaskList and is a child of Command class.
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
     * Makes the task requested by the user.
     * @return Task requested by the user.
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
            String eventDateAndTime = eventDesc[i + 1] + " " + eventDesc[i + 2];
            checkDateTimeFormat(eventDateAndTime);
            return new Event(eventDetails.toString(), eventDateAndTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("The right way to create an event,\n"
                    + "'event [description] /at YYYY-MM-DD HHmm' is!");
        }
    }

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
            String deadlineDateAndTime = deadlineDesc[i + 1] + " " + deadlineDesc[i + 2];
            checkDateTimeFormat(deadlineDateAndTime);
            return new Deadline(deadlineDetails.toString(), deadlineDateAndTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("The right way to create a deadline,\n"
                    + "'deadline [description] /by YYYY-MM-DD HHmm' is!");
        }
    }

    public void checkDateTimeFormat(String dateTime) throws InvalidDateTimeFormatException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("'YYYY-MM-DD HHmm', the right format is!\n"
                    + "2021-01-01 1600, an example is!");
        }
    }

    /**
     * Adds a specific type of task to the TaskList based on the command issued by the user.
     * @param taskList TaskList associated with the AddCommand being executed.
     * @param ui Ui associated with the AddCommand being executed.
     * @param storage Storage associated with the AddCommand being executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = makeTask();
            taskList.addTask(task);
            storage.write(taskList);
            return ui.printAddedTask(task);
        } catch (InvalidCommandFormatException | InvalidDateTimeFormatException e) {
            return e.getMessage();
        }
    }
}
