package duke.command;

import java.util.function.Function;

/**
 * This class encapsulates the user instruction,task and date and execute the instruction.
 */
public abstract class Command {
    private final String instruction;
    private final String task;
    private final String date;
    private boolean isExit;
    private final Function<Command, String> func;


    /**
     * Create a command with user instruction, task, date and a function to execute the instruction.
     *
     * @param instruction user instruction.
     * @param task        user task.
     * @param date        date of the task.
     * @param func        the function takes a command to execute the task and returns a boolean whether to end the program.
     */
    public Command(String instruction, String task, String date, boolean isExit, Function<Command, String> func) {
        this.instruction = instruction;
        this.task = task;
        this.date = date;
        this.isExit = isExit;
        this.func = func;
    }


    /**
     * Execution of a command and return a boolean whether to end the program.
     *
     * @return a boolean of whether to end the program.
     */
    public String execute() {
        return func.apply(this);
    }

    public boolean getIsExitStatus() {
        return isExit;
    }
    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }
}
