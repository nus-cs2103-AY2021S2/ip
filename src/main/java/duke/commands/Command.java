package duke.commands;

import duke.parser.DuplicateException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Command {
    private String[] userInput;
    private TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public Command(String[] userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Gets the type for the task of concern
     * @param parts Array containing the command entered by the user
     * @param delimiter String used for identifying due date of the task
     * @return Description of the task
     */
    protected String getDescription(String[] parts, String delimiter) {
        String description = "";
        boolean first = true;
        for (int i = 1; i < parts.length; ++i) {
            if (!parts[i].equals(delimiter)) {
                if (!first) {
                    description += " " + parts[i];
                } else {
                    description += parts[i];
                }
                first = false;
            } else {
                break;
            }
        }
        return description;
    }

    /**
     * Obtains the due date of the task of concern
     * @param parts Array containing the command entered by the user
     * @param delimiter String used for identifying due date of the task
     * @return Due date of the task of concern
     */
    protected LocalDate getDueDate(String[] parts, String delimiter) {
        boolean flag = false;
        boolean first = true;
        String dueDate = "";
        for (String s : parts) {
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

    public void hasDuplicate(String taskDescription) throws DuplicateException {
        ArrayList<Task> tasks = this.getTaskList().getList();
        for (Task t : tasks) {
            if (t.getDescription() == taskDescription) {
                throw new DuplicateException("The task has already been recorded!\n");
            }
        }
    }

    public abstract TaskList execute();

    public abstract Command process();

    public String[] getUserInput() {
        return this.userInput;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public abstract String toString();
}
