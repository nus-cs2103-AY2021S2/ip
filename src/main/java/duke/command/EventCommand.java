package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command {

    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("I apologize, please input description and time for 'event'.");
        } else {
            String[] split = arguments.split("/at");
            if (arguments.equals(split[0])) {
                throw new DukeException("I apologize, please use '/at' argument to specify time for 'event'.");
            } else {
                Task newTask = new Event(split[0].strip(), split[1].strip());
                taskList.add(newTask);
                storage.addToFile(newTask);
                ui.print("Added to to-do list: \n" + newTask);
            }
        }
    }
}
