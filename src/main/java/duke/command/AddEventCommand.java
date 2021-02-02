package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.MissingDateException;
import duke.exceptions.UnknownInputException;
import duke.tasks.Event;
import duke.tasks.TaskList;

public class AddEventCommand extends Command {
    private final Event event;

    public AddEventCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new UnknownInputException("event");
        }

        String[] name = description.split(" /at ", 2);

        if (name.length != 2) {
            throw new MissingDateException("");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(name[1], formatter);
        this.event = new Event(name[0], dateTime);
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);
        String result = ui.displayAddedTask(this.event, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
