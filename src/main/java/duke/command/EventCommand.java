package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.exception.DescriptionMissingException;
import duke.exception.InvalidDateTimeException;
import duke.parser.Parser;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    private final String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event = getTask();
        super.addThisTask(tasks, event, storage);
    }

    @Override
    public Event getTask() throws DescriptionMissingException, InvalidDateTimeException {
        String nameDate = fullCommand.substring(5).strip();
        if (nameDate.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }

        String[] nameAndDate = nameDate.split("/at");
        if (nameAndDate.length < 2) {
            throw new DescriptionMissingException("Argument missing!");
        }
        String name = nameAndDate[0].strip();
        String Date = nameAndDate[1].strip();

        LocalDateTime startTime = Parser.parseDateTime(Date);
        return new Event(name, startTime);
    }
}
