package duke.commands;

import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.*;
import duke.ui.Ui;

public class AddCommand extends Command {
    public AddCommand(String description) {
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskItem;
        String[] inputList = description.trim().split(" ", 2);

        if (inputList.length != 2) {
            throw new IncompleteCommandException();
        }

        String command = inputList[0].trim();

        if (Parser.isToDo(command)) {
            taskItem = new ToDo(inputList[1].trim());
        } else if (Parser.isDeadline(command)) {
            String[] taskDeadline = inputList[1].trim().split("/by", 2);

            if (taskDeadline.length != 2) {
                throw new IncompleteCommandException();
            }
            try {
                taskItem = new Deadline(taskDeadline[0].trim(), taskDeadline[1].trim());
            } catch (Exception e) {
                throw new DeadLineException("test deadline exception");
            }
        } else if (Parser.isEvent(command)) {
            String[] taskEvent = inputList[1].trim().split("/at", 2);

            if (taskEvent.length != 2) {
                throw new IncompleteCommandException();
            }
            try {
                taskItem = new Event(taskEvent[0].trim(), taskEvent[1].trim());
            } catch (Exception e) {
                throw new EventException("test event exception");
            }
        } else {
            throw new InvalidCommandException();
        }
        taskList.addTask(taskItem);
        return ui.addedTask(taskItem);
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
