package duke.commands;

import duke.parser.DateCommandException;
import duke.tasks.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class DateCommand extends Command {
    protected String taskDescription;
    protected LocalDate date;

    public DateCommand(TaskList taskList, String taskDescription, LocalDate date) {
        super(taskList);
        this.taskDescription = taskDescription;
        this.date = date;
    }

    public DateCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = "";
        this.date = null;
    }

    /**
     * Checks if a date related command entered by the user is valid.
     * The command should contain the command type, the task description and the date of task.
     *
     * @param input String array containing the command entered by the user.
     * @param delimiter String used for identifying date of the task.
     * @throws DateCommandException If the date-related command entered
     * by the user has insufficient parameters.
     */
    protected void checkValidity (String[] input, String delimiter) throws DateCommandException {
        int delimiterIndex = -10;
        for (int i = 0; i < input.length; ++i) {
            if (input[i].equals(delimiter)) {
                delimiterIndex = i;
                break;
            }
        }
        System.out.println(delimiterIndex);
        if (delimiterIndex < 0 || input.length < 4) {
            throw new DateCommandException("Please enter the command in the following format:\n"
                    + input[0] + " task_description " + delimiter + " date(yyyy-mm-dd)");
        }
    }

    /**
     * Obtains the due date of the task of concern.
     *
     * @param input String array containing the command entered by the user.
     * @param delimiter String used for identifying date of the task.
     * @return Due date for the task of concern.
     */
    protected LocalDate extractDateFromCommand(String[] input, String delimiter)
            throws DateTimeParseException {
        boolean flag = false;
        boolean first = true;
        String dueDate = "";
        for (String s : input) {
            if (flag) {
                if (!first) {
                    dueDate += ' ' + s;
                } else {
                    dueDate += s;
                }
                first = false;
            }
            if (s.equals(delimiter)) {
                flag = true;
            }
        }
        return LocalDate.parse(dueDate);
    }

    public abstract TaskList execute();

    public abstract Command process();

}
