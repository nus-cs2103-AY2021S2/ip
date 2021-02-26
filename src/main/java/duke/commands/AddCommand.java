package duke.commands;

import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.*;
import duke.ui.Ui;

/**
 * A command class that adds a new task into the taskList
 */
public class AddCommand extends Command {
    public AddCommand(String description) {
        super(description);
    }

    /**
     * Adds a new task to taskList
     *
     * @param taskList the list of tasks
     * @param ui the UI object
     * @param storage the storage object
     * @return String message based on AddCommand
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskItem = new Task("placebo task", taskType.TASK);
        String[] inputList = description.trim().split(" ", 2);

        assert(inputList.length >= 2);

        if (inputList.length < 2) {
            throw new IncompleteCommandException();
        }

        String command = inputList[0].trim();

        if (Parser.isToDo(command)) {
            taskItem = new ToDo(inputList[1].trim());
        } else if (Parser.isDeadline(command)) {
            String[] taskDeadline = inputList[1].trim().split("/by", 2);

            try {
                taskItem = new Deadline(taskDeadline[0].trim(), taskDeadline[1].trim());
            } catch (Exception e) {
                throw new DeadLineException();
            }
        } else if (Parser.isEvent(command)) {
            String[] taskEvent = inputList[1].trim().split("/at", 2);

            try {
                taskItem = new Event(taskEvent[0].trim(), taskEvent[1].trim());
            } catch (Exception e) {
                throw new EventException();
            }
        } else {
            throw new InvalidCommandException();
        }
        taskList.addTask(taskItem);
        return ui.addedTask(taskItem, taskList.getSize());
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
