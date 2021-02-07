package duke;

/**
 * Represents a task listed in Duke and can be marked as done. Can statically create subclasses of Task, namely:
 * Event, Deadline, ToDo
 */
public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Creates a task with the specified description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Passes the command to the relevant task creation methods, namely:
     * createEvent, createDeadline, createToDo
     *
     * @param taskCommand String of command that describes the task to be created.
     * @throws DukeException if task creation is invalid.
     */
    public static Task dispatchTaskCreation(String taskCommand) throws DukeException {
        Task newTask;
        taskCommand = taskCommand.trim();
        switch (taskCommand.split(" ")[0]) {
        case "todo":
            newTask = createToDo(taskCommand);
            break;
        case "deadline":
            newTask = createDeadline(taskCommand);
            break;
        case "event":
            newTask = createEvent(taskCommand);
            break;
        default:
            throw new DukeException("DukeObjects.Duke.Task cannot be created: " + taskCommand);
        }
        return newTask;
    }

    /**
     * Creates ToDo Task from a command with todo.
     *
     * @param command User input with the word todo.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     */
    private static Task createToDo(String command) throws DukeException {
        int lengthOfTaskDescription = command.substring(4).trim().length();
        if (lengthOfTaskDescription == 0) {
            throw new DukeException("Expected argument describing task after \"todo\"");
        }
        return new ToDo(command.substring(5));
    }

    /**
     * Creates Task from a command with deadline.
     *
     * @param command User input with the word deadline.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no deadline specified by "/by".
     */
    private static Task createDeadline(String command) throws DukeException {
        String[] splitCommand = command.substring(8).split("/by");
        if (splitCommand[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"deadline\"");
        }
        if (splitCommand.length == 1 || splitCommand[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/by\" specifying deadline of task for \"deadline\"");
        }
        if (splitCommand.length != 2) {
            throw new DukeException("Multiple \"/by\" not allowed in for \"deadline\"");
        }
        return new Deadline(splitCommand[0].trim(), splitCommand[1].trim());
    }

    /**
     * Creates Task from a command with event.
     *
     * @param command User input with the word event.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no duration specified by "/at".
     */
    private static Task createEvent(String command) throws DukeException {
        String[] splitCommand = command.substring(5).split("/at");
        if (splitCommand[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"event\"");
        }
        if (splitCommand.length == 1 || splitCommand[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/at\" specifying duration of task for \"event\"");
        }
        if (splitCommand.length != 2) {
            throw new DukeException("Multiple \"/at\" not allowed in for \"event\"");
        }
        return new Event(splitCommand[0].trim(), splitCommand[1].trim());
    }

    @Override
    public String toString() {
        String symbol = " ";
        if (isDone) {
            symbol = "X";
        }
        return "[" + symbol + "] " + this.description;
    }
}
