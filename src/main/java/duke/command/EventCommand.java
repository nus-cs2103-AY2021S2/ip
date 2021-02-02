package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends AddTaskCommand {
    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] argumentsArr = this.arguments.split(" /at ", 2);
        tasks.add(new Event(argumentsArr[0], argumentsArr[1]));
        super.execute(tasks, ui, storage);
    }
}
