package command;

import Exceptions.DukeException;
import Exceptions.MissingDateException;
import Exceptions.UnknownInputException;
import ui.Ui;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);
        ui.displayAddedTask(this.event, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
