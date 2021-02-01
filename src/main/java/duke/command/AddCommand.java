package duke.command;

import duke.DukeException;
import duke.Helper;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskException;
import duke.task.ToDo;

public class AddCommand extends Command {
    public AddCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Checks what type of add command it is (event, to do, deadline) then call the respective functions
     * e.g. addDeadline to add a new task.
     * @param list the task list.
     * @throws DukeException if adding a task failed.
     */
    @Override
    public void execute(TaskList list) throws DukeException {
        String keyword = commandSplit[0];
        if (keyword.equals("deadline")) {
            try {
                addDeadline(list);
            } catch (TaskException e) {
                throw new DukeException("Failed to add deadline to tasks. " + e.getMessage());
            }
        } else if (keyword.equals("todo")) {
            addToDo(list);
        } else {
            try {
                addEvent(list);
            } catch (TaskException e) {
                throw new DukeException("Failed to add event to tasks. " + e.getMessage());
            }
        }
    }

    private void addDeadline(TaskList list) throws DukeException, TaskException {
        String[] userInputSplit = this.commandSplit;
        //Index of /by keyword
        int byIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == 0) {
            throw new DukeException("Missing /by keyword for new deadline.");
        } else if (byIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (byIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the deadline.");
        }
        String task = Helper.join(userInputSplit, 1, byIndex - 1);
        String date = Helper.join(userInputSplit, byIndex + 1, userInputSplit.length - 1);
        list.add(new Deadline(task, date));
    }

    private void addToDo(TaskList list) throws DukeException {
        String[] userInputSplit = this.commandSplit;
        if (userInputSplit.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String task = Helper.join(userInputSplit, 1, userInputSplit.length - 1);
        list.add(new ToDo(task));
    }

    private void addEvent(TaskList list) throws DukeException, TaskException {
        String[] userInputSplit = this.commandSplit;
        //Index of /at keyword
        int atIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/at")) {
                atIndex = i;
            }
        }
        if (atIndex == 0) {
            throw new DukeException("Missing /at keyword for new Event.");
        } else if (atIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
        }
        if (atIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the Event.");
        }
        String task = Helper.join(userInputSplit, 1, atIndex - 1);
        String date = Helper.join(userInputSplit, atIndex + 1, userInputSplit.length - 1);
        list.add(new Event(task, date));
    }
}
