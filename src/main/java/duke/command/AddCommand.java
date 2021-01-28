package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;

class AddCommand extends Command{

    AddCommand(String command, String description, String preposition, LocalDate date) {
        super(command, description, preposition, date, false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = getCorrectTask();
        tasks.add(t);
        storage.save(tasks.listOutTaskInString());
    }

    Task getCorrectTask() {
        switch(command) {
            case "todo":
                return new Todo(description);
            case "event":
                return new Event(description, preposition, date);
            case "deadline":
                return new Deadline(description, preposition, date);
            default:
                return null;
        }
    }
}
