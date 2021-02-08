package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * A TaskList class to represent a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if given String is not Numeric.
     *
     * @param str String to be checked.
     * @return True if not numeric, False otherwise.
     */
    private static boolean isNotNumeric(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return !str.chars().allMatch(Character::isDigit);
    }

    /**
     * Lists all tasks.
     *
     * @return Output for GUI.
     */
    public String list() {
        StringBuilder sb = new StringBuilder("     Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            String msg = "     " + (i + 1) + "." + currTask.toString() + "\n";
            sb.append(msg);
        }
        return sb.toString();
    }

    /**
     * Completes a task in the TaskList.
     *
     * @param fullCommand Command given by user.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    public String done(String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        if (inputArr.length > 2) {
            throw new DukeException("       OOPS!!! The format should be "
                    + "\"done ##\" where ## is the task number.");
        }
        if (inputArr.length == 1) {
            throw new DukeException("       OOPS!!! The task number cannot be empty.");
        }
        if (isNotNumeric(inputArr[1])) {
            throw new DukeException("       OOPS!!! The task number must be numeric.");
        }
        int i = Integer.parseInt(inputArr[1]) - 1;
        if (i > (this.tasks.size() - 1) || i < 0) {
            throw new DukeException("       OOPS!!! The task number is out of range. "
                    + "Please use \"list\" to see the list of tasks.");
        } else {
            this.tasks.get(i).markAsDone();
            Task currTask = tasks.get(i);
            return "     Nice! I've marked this task as done:\n"
                    + "       " + currTask.toString() + "\n";
        }
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param fullCommand Command given by user.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    public String delete(String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        if (inputArr.length > 2) {
            throw new DukeException("       OOPS!!! The format should be "
                    + "\"delete ##\" where ## is the task number.");
        }
        if (inputArr.length == 1) {
            throw new DukeException("       OOPS!!! The task number cannot be empty.");
        }
        if (isNotNumeric(inputArr[1])) {
            throw new DukeException("       OOPS!!! The task number must be numeric.");

        }
        int i = Integer.parseInt(inputArr[1]) - 1;
        if (i > (this.tasks.size() - 1) || i < 0) {
            throw new DukeException("       OOPS!!! The task number is out of range. "
                    + "Please use \"list\" to see the list of tasks.");
        } else {
            return "     Noted. I've removed this task:\n"
                    + "       " + this.tasks.remove(i).toString() + "\n"
                    + "     Now you have " + this.tasks.size() + " tasks in the list.\n";
        }
    }

    /**
     * Finds all task in task list that contains a given keyword.
     *
     * @param fullCommand Command given by user.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    public String find(String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        if (inputArr.length > 2) {
            throw new DukeException("       OOPS!!! The format should be "
                    + "\"find ####\" where #### is the keyword to search.");
        }
        if (inputArr.length == 1) {
            throw new DukeException("       OOPS!!! The keyword cannot be empty.");
        }
        StringBuilder sb = new StringBuilder("     Here are the matching tasks in your list:\n");
        ArrayList<Task> temp = new ArrayList<>();
        for (Task currTask : tasks) {
            String currDescription = currTask.getDescription();
            if (currDescription.contains(inputArr[1])) {
                temp.add(currTask);
            }
        }
        for (int j = 0; j < temp.size(); j++) {
            Task tempTask = temp.get(j);
            String msg = "     " + (j + 1) + "." + tempTask.toString() + "\n";
            sb.append(msg);
        }
        return sb.toString();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param type        The type of task.
     * @param fullCommand Command given by user.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    public String addTask(String type, String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        String[] details = new String[]{};
        LocalDate date = LocalDate.now();

        if (inputArr.length == 1) {
            throw new DukeException("       OOPS!!! The description of the task cannot be empty.");
        }

        if (!type.equals("todo")) {
            if (type.equals("deadline")) {
                details = fullCommand.substring(9).split(" /by ");
            } else {
                details = fullCommand.substring(6).split(" /at ");
            }
            if (details.length == 1) {
                throw new DukeException("       OOPS!!! The date of a deadline/event cannot be empty.");
            }
            try {
                date = LocalDate.parse(details[1]);
            } catch (DateTimeParseException ex) {
                throw new DukeException("       OOPS!!! The date has to be in the format yyyy-mm-dd.");
            }
        }

        switch (type) {
        case "todo":
            this.tasks.add(new Todo(0, fullCommand.substring(5)));
            break;
        case "deadline":
            this.tasks.add(new Deadline(0, details[0], date));
            break;
        case "event":
            this.tasks.add(new Event(0, details[0], date));
            break;
        default:
        }
        return "     Got it. I've added this task:\n"
                + "       " + this.tasks.get(this.tasks.size() - 1).toString() + "\n"
                + "     Now you have " + this.tasks.size() + " tasks in the list.\n";
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
