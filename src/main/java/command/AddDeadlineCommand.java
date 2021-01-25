package command;

import Exceptions.DukeException;
import Exceptions.MissingDateException;
import Exceptions.UnknownInputException;
import ui.Ui;
import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand extends Command{
    private final Deadline deadline;

    public AddDeadlineCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new UnknownInputException("deadline");
        }
        String[] name = description.split(" /by ", 2);

        if (name.length != 2) {
            throw new MissingDateException("");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(name[1], formatter);
        this.deadline = new Deadline(name[0], dateTime);

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);
        ui.displayAddedTask(this.deadline, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
    }

    public boolean isExit() {
         return false;
    }


}
