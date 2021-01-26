package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionCommandNotFound;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class DukeCommand {

    public boolean isExit() {
        return false;
    }

    public static DukeCommand parse(String line)
            throws DukeExceptionCommandNotFound, DukeExceptionIllegalArgument {
        line = line.strip(); // input sanitization
        String arg = "", cmd = line;
        if (line.contains(" ")) {
            int splitIdx = line.indexOf(' ');
            cmd = line.substring(0, splitIdx); // automatically stripped
            arg = line.substring(splitIdx+1).strip();
        }
        cmd = cmd.toLowerCase();

        Task task;
        switch (cmd) {
        case "bye":
            return new DukeCommandBye();
        case "list":
            return new DukeCommandList(arg);
        case "done":
            return new DukeCommandDone(arg);
        case "delete":
            return new DukeCommandDelete(arg);
        case "event":
            task = Event.parse(arg);
            return new DukeCommandAdd(task);
        case "todo":
            task = Todo.parse(arg);
            return new DukeCommandAdd(task);
        case "deadline":
            task = Deadline.parse(arg);
            return new DukeCommandAdd(task);
        case "find":
            return new DukeCommandFind(arg);
        default:
            throw new DukeExceptionCommandNotFound("Command '" + cmd + "' is invalid. Valid commands:"
                    + "\nbye, list, done, delete, event, todo, deadline");
        }
    }

    public abstract void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeException;
}
