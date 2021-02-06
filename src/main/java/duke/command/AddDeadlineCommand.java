package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.MissingDateException;
import duke.exceptions.UnknownInputException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    public AddDeadlineCommand(String descriptionString) throws DukeException {
        if (descriptionString.isBlank()) {
            throw new UnknownInputException("deadline");
        }

        String[] name = descriptionString.split(" /by ", 2);

        if (name.length != 2) {
            throw new MissingDateException("");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(name[1], formatter);
        this.deadline = new Deadline(name[0], dateTime);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);
        String result = ui.displayAddedTask(this.deadline, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
        return result;
    }


    public boolean isExit() {
        return false;
    }


}
