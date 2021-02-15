package moomin.command;

import moomin.exception.DukeException;
import moomin.parser.Parser;
import moomin.storage.Storage;
import moomin.task.Deadline;
import moomin.task.Task;
import moomin.task.TaskList;
import moomin.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private static final String EMPTY_DEADLINE_ERROR_MESSAGE = "You can't create an empty deadline!";
    private static final String NO_DATETIME_ERROR_MESSAGE = "You can't add a deadline without a datetime!";
    private static final String WRONG_DATETIME_FORMAT_ERROR_MESSAGE =
            "Please follow the datetime format of dd/mm/yyyy hhmm.";

    private Deadline deadline;

    public AddDeadlineCommand(String details) throws DukeException {
        try {
            if (details.isEmpty()) {
                throw new DukeException(EMPTY_DEADLINE_ERROR_MESSAGE);
            }
            assert !details.isEmpty() : EMPTY_DEADLINE_ERROR_MESSAGE;
            String[] detailsArr = details.split(" /by ", 2);
            if (detailsArr.length != 2) {
                throw new DukeException(NO_DATETIME_ERROR_MESSAGE);
            }
            assert detailsArr.length == 2 : NO_DATETIME_ERROR_MESSAGE;

            LocalDateTime date = Parser.parseDateTimeFromInput(detailsArr[1]);
            this.deadline = new Deadline(detailsArr[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException(WRONG_DATETIME_FORMAT_ERROR_MESSAGE);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(deadline);
        storage.setMostRecentCommand(this);
        storage.saveTasksToFile(tasks);
        return ui.getAddTaskReport(deadline, tasks);

    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(tasks.getTaskCount() - 1);
        tasks.deleteTask(tasks.getTaskCount() - 1);
        storage.saveTasksToFile(tasks);
        return ui.getUndoAddTaskMessage(task, tasks);
    }


    @Override
    public boolean isExitCommand() {
        return false;
    }
}
