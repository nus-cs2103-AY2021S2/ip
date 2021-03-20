package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class AddCommand extends Command {

    private static String DUPLICATE_TASK_MESSAGE = "A task with the same name has already been added!"
            + "\nPlease do not add tasks with a duplicate name.";
    private static String MISSING_NAME_AND_DATE_MESSAGE = "OOPS!!! The name and date of the task cannot be empty. \n";
    private static String MISSING_NAME_MESSAGE = "OOPS!!! The name of the task cannot be empty. ";
    private static String WRONG_FORMAT_MESSAGE = "OOPS!!! Please follow the format\n";
    private static String SUCCESSFUL_ADD_MESSAGE = "Got it. I have added this task : \n";

    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add command.
     * Adds a new task to the task list.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     * @throws IOException If an input or output exception occurred
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        int indexOfSpace = fullCommand.indexOf(" ");
        String taskType = fullCommand.split(" ")[0];
        Task taskToAdd = null;
        if (fullCommand.equals("todo")) {
            return MISSING_NAME_MESSAGE;
        }
        if (fullCommand.equals("event")) {
            return MISSING_NAME_AND_DATE_MESSAGE + Event.getEventFormat();
        }
        if (fullCommand.equals("deadline")) {
            return MISSING_NAME_AND_DATE_MESSAGE + Deadline.getDeadlineFormat();
        }
        if (taskType.equals("todo")) {
            String toDoName = fullCommand.substring(indexOfSpace + 1).trim();
            if (isDuplicateTask(tasks, toDoName)) {
                return DUPLICATE_TASK_MESSAGE;
            }
            taskToAdd = new ToDo(toDoName);
        } else if (taskType.equals("deadline")) {
            int indexOfDivider = fullCommand.indexOf("/by");
            if (indexOfDivider == -1) {
                return WRONG_FORMAT_MESSAGE + Deadline.getDeadlineFormat();
            }
            String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
            if (isDuplicateTask(tasks, taskName)) {
                return DUPLICATE_TASK_MESSAGE;
            }
            if (taskName.equals("")) {
                return MISSING_NAME_MESSAGE + "\n" + Deadline.getDeadlineFormat();
            }
            try {
                taskToAdd = commandToTask(fullCommand, taskType);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return WRONG_FORMAT_MESSAGE + Deadline.getDeadlineFormat();
            }
        } else if (taskType.equals("event")) {
            int indexOfDivider = fullCommand.indexOf("/at");
            if (indexOfDivider == -1) {
                return WRONG_FORMAT_MESSAGE + Event.getEventFormat();
            }
            String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
            if (isDuplicateTask(tasks, taskName)) {
                return DUPLICATE_TASK_MESSAGE;
            }
            if (taskName.equals("")) {
                return MISSING_NAME_MESSAGE + "\n" + Event.getEventFormat();
            }
            try {
                taskToAdd = commandToTask(fullCommand, taskType);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return WRONG_FORMAT_MESSAGE + Event.getEventFormat();
            }
        }
        taskList.add(taskToAdd);
        storage.saveTasksToFile(taskList);
        String output = SUCCESSFUL_ADD_MESSAGE;
        output += taskToAdd.toString() + "\n";
        output += String.format("Now you have %d tasks in the list.", taskList.size());
        return output;
    }


    /**
     * Transforms a String to a Task object.
     *
     * @param fullCommand the command input from user.
     * @param taskType the type of the task.
     *
     * @return the transformed task.
     */
    public Task commandToTask(String fullCommand, String taskType) {
        int indexOfSpace = fullCommand.indexOf(" ");
        Task taskToAdd = null;
        int indexOfDivider = fullCommand.indexOf("/");
        String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
        String dateString = fullCommand.substring(indexOfDivider + 4);
        LocalDate date = getLocalDateFromString(dateString);
        if (taskType.equals("deadline")) {
            taskToAdd = new Deadline(taskName, date);
        } else {
            assert taskType.equals("event");
            taskToAdd = new Event(taskName, date);
        }
        return taskToAdd;

    }

    /**
     * Transforms a String to a LocalDate object.
     *
     * @param dateString the date information in String format.
     *
     * @return the transformed LocalDate object.
     */
    public LocalDate getLocalDateFromString(String dateString) {
        int indexOfFirstDivider = dateString.indexOf('-');
        int year = Integer.valueOf(dateString.substring(0, indexOfFirstDivider));
        String monthAndDay = dateString.substring(indexOfFirstDivider + 1);
        int indexOfSecondDivider = monthAndDay.indexOf('-');
        int mon = Integer.valueOf(monthAndDay.substring(0, indexOfSecondDivider));
        int day = Integer.valueOf(monthAndDay.substring(indexOfSecondDivider + 1));
        LocalDate date = LocalDate.of(year, mon, day);
        return date;
    }


    /**
     * Checks if a specific task exists in the task list.
     *
     * @param tasks the list of tasks.
     * @param taskName the name of the task to look up.
     *
     * @return if the same task exists.
     */
    public Boolean isDuplicateTask(TaskList tasks, String taskName) {
        ArrayList<Task> taskList = tasks.getTasks();
        for (Task task : taskList) {
            if (task.getName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }



}
