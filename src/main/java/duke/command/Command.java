package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    protected String commandType;
    protected String commandDetails;
    protected int index;
    protected String dateTime;
    protected String outputMessage;

    public String getTaskDetails() {
        return String.format("%s %s %s", commandType, commandDetails, dateTime);
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean continueInput();
}
