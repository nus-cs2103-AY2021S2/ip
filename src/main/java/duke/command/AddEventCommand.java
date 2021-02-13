package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;

public class AddEventCommand extends Command {

    private static final String addSuccess = "Added to collection.";
    private static final Boolean toExit = false;

    private Event event;

    /**
     * Initialises add event command by specifying
     * its description and event date time.
     *
     * @param desc Event description.
     * @param dateTime Event date time.
     */
    public AddEventCommand(String desc, LocalDate dateTime) {
        this.event = new Event(desc, dateTime);
    }

    /**
     * Adds event into the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If invalid task type or arguments specified.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.event);
        return new CommandResponse(AddEventCommand.addSuccess, AddEventCommand.toExit);
    }
}
