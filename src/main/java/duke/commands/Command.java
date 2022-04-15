package duke.commands;

import duke.tasks.TaskList;

/**
 * Command is a parent class for inheriting by specific commands.
 */
public abstract class Command {
    private String[] userInput;
    private TaskList taskList;

    protected Command(TaskList taskList) {
        this.taskList = taskList;
    }

    protected Command(String[] userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Gets the task description from the user input.
     *
     * @param input String array containing the command entered by the user.
     * @param delimiter String used for identifying date of the tasks.
     * @return A String description of the task.
     */
    protected String getDescription(String[] input, String delimiter) {
        String description = "";
        boolean first = true;
        for (int i = 1; i < input.length; ++i) {
            if (!input[i].equals(delimiter)) {
                if (!first) {
                    description += " " + input[i];
                } else {
                    description += input[i];
                }
                first = false;
            } else {
                break;
            }
        }
        return description;
    }

    public abstract TaskList execute();

    public abstract Command process();

    public String[] getUserInput() {
        return this.userInput;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
