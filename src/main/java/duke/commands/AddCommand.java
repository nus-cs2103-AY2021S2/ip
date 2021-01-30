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
     * Adds the specified task into the TaskList given by command line
     * and save changes into the save file.
     *
     * @param tasks TaskList to add task into.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @throws DukeException If error happens while adding task.
     * @throws DateTimeParseException If date and time format in command is incorrect.
     * @throws StringIndexOutOfBoundsException If format in command is incorrect.
     * @throws IOException If error happens while saving contents into file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException,
            DateTimeParseException, StringIndexOutOfBoundsException, IOException {
        Task temp;
        String description;
        String dateTime;

        if (checkCommands[0].equals("todo") || checkCommands[0].equals("deadline")
                || checkCommands[0].equals("event")) {
            if (checkCommands.length == 1) {
                throw new DescriptionNotFoundException();
            }
            if (checkCommands[0].equals("todo")) {
                description = fullCommand.substring(5).trim();
                temp = new TodoTask(description);
            } else if (checkCommands[0].equals("deadline")) {
                int index = fullCommand.indexOf("/by");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = fullCommand.substring(9, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = fullCommand.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate
                        .parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new DeadlineTask(description, date);
                } else {
                    LocalTime time = LocalTime
                            .parse(dateTime.substring(10).trim(), DateTimeFormatter.ofPattern("HHmm"));
                    temp = new DeadlineTask(description, date, time);
                }
            } else {
                int index = fullCommand.indexOf("/at");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = fullCommand.substring(6, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = fullCommand.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate
                        .parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new EventTask(description, date);
                } else {
                    String startTimeString = timeString.substring(0, 4);
                    LocalTime startTime = LocalTime
                            .parse(startTimeString, DateTimeFormatter.ofPattern("HHmm"));
                    String endTimeString = timeString.substring(4).trim();
                    if (endTimeString.isEmpty()) {
                        temp = new EventTask(description, date, startTime);
                    } else {
                        LocalTime endTime = LocalTime
                                .parse(endTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        if (endTime.compareTo(startTime) < 0) {
                            throw new TimeDurationInvalidException();
                        }
                        temp = new EventTask(description, date, startTime, endTime);
                    }
                }
            }
        } else {
            throw new CommandNotValidException();
        }
        tasks.add(temp);
        ui.showAddTask(tasks, temp);
        storage.save(tasks);
    }

    /**
     * Returns if program should exit after this command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
