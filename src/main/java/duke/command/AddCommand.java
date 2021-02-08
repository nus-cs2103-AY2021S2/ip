package duke.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import duke.DukeException;
import duke.Helper;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskException;
import duke.task.ToDo;

public class AddCommand extends Command {

    private static boolean isValidAddKeyword(String keyword) {
        Set<String> validKeywords = new HashSet<>(Arrays.asList("deadline", "event", "todo"));
        return validKeywords.contains(keyword);
    }

    public AddCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length > 1 && isValidAddKeyword(commandSplit[0]): "Add command should have valid keyword.";
    }

    /**
     * Checks what type of add command it is (event, to do, deadline) then call the respective functions
     * e.g. addDeadline to add a new task.
     * @param list the task list.
     * @return response that the task has been added.
     * @throws DukeException if adding a task failed.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        String keyword = commandSplit[0];
        if (keyword.equals("deadline")) {
            try {
                return addDeadline(list);
            } catch (TaskException e) {
                throw new DukeException("Failed to add deadline to tasks. " + e.getMessage());
            }
        } else if (keyword.equals("todo")) {
            return addToDo(list);
        } else {
            try {
                return addEvent(list);
            } catch (TaskException e) {
                throw new DukeException("Failed to add event to tasks. " + e.getMessage());
            }
        }
    }

    private String addDeadline(TaskList list) throws DukeException, TaskException {
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
        return list.add(new Deadline(task, date));
    }

    private String addToDo(TaskList list) throws DukeException {
        String[] userInputSplit = this.commandSplit;
        if (userInputSplit.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String task = Helper.join(userInputSplit, 1, userInputSplit.length - 1);
        return list.add(new ToDo(task));
    }

    private String addEvent(TaskList list) throws DukeException, TaskException {
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
        return list.add(new Event(task, date));
    }
}
