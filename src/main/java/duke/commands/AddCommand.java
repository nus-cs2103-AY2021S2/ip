package duke.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.CommandNotValidException;
import duke.exceptions.DateTimeNotFoundException;
import duke.exceptions.DescriptionNotFoundException;
import duke.exceptions.DukeException;
import duke.exceptions.TimeDurationInvalidException;
import duke.storage.Storage;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;
import duke.ui.Ui;

/**
 * Responsible for dealing with the addition of tasks.
 */
public class AddCommand extends Command {
    private String fullCommand;
    private String[] checkCommands;

    /**
     * Constructs a AddCommand with the given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.checkCommands = fullCommand.split(" ");
    }

    /**
     * Returns the string to indicate successful addition of task.
     * Adds the specified task into the TaskList given by command line
     * and save changes into the save file.
     *
     * @param tasks TaskList to add task into.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return Successful addition of task string.
     * @throws DukeException If error happens while adding task.
     * @throws DateTimeParseException If date and time format in command is incorrect.
     * @throws StringIndexOutOfBoundsException If format in command is incorrect.
     * @throws IOException If error happens while saving contents into file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException,
            DateTimeParseException, StringIndexOutOfBoundsException, IOException {

        Task task = createTasks();
        tasks.add(task);
        storage.save(tasks);
        return ui.getAddTaskString(tasks, task);
    }

    private Task createTasks() throws DukeException,
            DateTimeParseException, StringIndexOutOfBoundsException {
        if (isValidCommand()) {
          
            assert checkCommands.length > 0;
          
            if (hasNoDescription()) {
                throw new DescriptionNotFoundException();
            }
            if (isTodoCommand()) {
                return createTodoTask();
            } else if (isDeadlineCommand()) {
                return createDeadlineTask();
            } else {
                return createEventTask();
            }
        } else {
            throw new CommandNotValidException();
        }
    }

    private boolean isValidCommand() {
        return checkCommands[0].equals("todo") || checkCommands[0].equals("deadline")
                || checkCommands[0].equals("event");
    }

    private boolean hasNoDescription() {
        return checkCommands.length == 1;
    }

    private boolean isTodoCommand() {
        return checkCommands[0].equals("todo");
    }

    private boolean isDeadlineCommand() {
        return checkCommands[0].equals("deadline");
    }

    private TodoTask createTodoTask() {
        String description = fullCommand.substring(5).trim();
        return new TodoTask(description);
    }

    private DeadlineTask createDeadlineTask() throws
            DateTimeNotFoundException, DescriptionNotFoundException {
        int timeIndex = fullCommand.indexOf("/by");
        if (hasNoDateTime(timeIndex)) {
            throw new DateTimeNotFoundException();
        }

        String description = fullCommand.substring(9, timeIndex).trim();
        if (hasNoDescription(description)) {
            throw new DescriptionNotFoundException();
        }

        String dateTime = fullCommand.substring(timeIndex + 3).trim();
        if (hasNoDateTime(dateTime)) {
            throw new DateTimeNotFoundException();
        }

        String dateString = dateTime.substring(0, 10);
        LocalDate date = LocalDate
                .parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String timeString = dateTime.substring(10).trim();
        if (timeString.isEmpty()) {
            return new DeadlineTask(description, date);
        } else {
            return createDeadlineTaskWithTime(dateTime, description, date);
        }
    }

    private DeadlineTask createDeadlineTaskWithTime(String dateTime, String description, LocalDate date) {
        LocalTime time = LocalTime
                .parse(dateTime.substring(10).trim(), DateTimeFormatter.ofPattern("HHmm"));
        return new DeadlineTask(description, date, time);
    }

    private boolean hasNoDateTime(int timeIndex) {
        return timeIndex == -1;
    }

    private boolean hasNoDateTime(String dateTime) {
        return dateTime.isEmpty();
    }

    private boolean hasNoDescription(String description) {
        return description.isEmpty();
    }

    private EventTask createEventTask() throws
            DateTimeNotFoundException, DescriptionNotFoundException, TimeDurationInvalidException {
        int timeIndex = fullCommand.indexOf("/at");
        if (hasNoDateTime(timeIndex)) {
            throw new DateTimeNotFoundException();
        }

        String description = fullCommand.substring(6, timeIndex).trim();
        if (hasNoDescription(description)) {
            throw new DescriptionNotFoundException();
        }

        String dateTime = fullCommand.substring(timeIndex + 3).trim();
        if (hasNoDateTime(dateTime)) {
            throw new DateTimeNotFoundException();
        }

        String dateString = dateTime.substring(0, 10);
        LocalDate date = LocalDate
                .parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String timeString = dateTime.substring(10).trim();
        if (hasNoTime(timeString)) {
            return new EventTask(description, date);
        } else {
            return createEventTaskWithTime(timeString, description, date);
        }
    }

    private boolean hasNoTime(String timeString) {
        return timeString.isEmpty();
    }

    private EventTask createEventTaskWithTime(String timeString, String description, LocalDate date)
            throws TimeDurationInvalidException {
        String startTimeString = timeString.substring(0, 4);
        LocalTime startTime = LocalTime
                .parse(startTimeString, DateTimeFormatter.ofPattern("HHmm"));
        String endTimeString = timeString.substring(4).trim();
        if (endTimeString.isEmpty()) {
            return createEventTaskWithStartTime(description, date, startTime);
        } else {
            return createEventTaskWithEndTime(description, endTimeString, date, startTime);
        }
    }

    private EventTask createEventTaskWithStartTime(String description, LocalDate date, LocalTime startTime) {
        return new EventTask(description, date, startTime);
    }

    private EventTask createEventTaskWithEndTime(String description,
            String endTimeString, LocalDate date, LocalTime startTime) throws TimeDurationInvalidException {
        LocalTime endTime = LocalTime
                .parse(endTimeString, DateTimeFormatter.ofPattern("HHmm"));
        if (endTime.compareTo(startTime) < 0) {
            throw new TimeDurationInvalidException();
        }
        return new EventTask(description, date, startTime, endTime);
    }
}
