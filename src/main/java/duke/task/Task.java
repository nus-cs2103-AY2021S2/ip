package duke.task;

import duke.DukeException;

/**
 * Represents a task listed in Duke and can be marked as done. Can statically
 * create subclasses of Task, namely: Event, Deadline, ToDo
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
            throw new DukeException("Task cannot be created: " + taskCommand);
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
        int charactersInTodo = 4;
        String commandDescription = command.substring(charactersInTodo);
        if (commandDescription.trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"todo\"");
        }
        String descriptionOfTodo = command.split(" ", 2)[1];
        return new ToDo(descriptionOfTodo);
    }

    /**
     * Creates Task from a command with deadline.
     *
     * @param command User input with the word deadline.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no deadline specified by "/by".
     * @throws DukeException if there are multiple "/by".
     */
    private static Task createDeadline(String command) throws DukeException {
        int charactersInDeadline = 8;
        String commandDescription = command.substring(charactersInDeadline);
        String[] splitDescription = commandDescription.split("/by");
        if (splitDescription[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"deadline\"");
        }
        // If there is only the description of the task and no deadline,
        // or if the deadline is whitespace, throw DukeException.
        // Must check length before checking 2nd argument or else nullPointerException.
        if (splitDescription.length == 1 || splitDescription[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/by\" specifying deadline of task for \"deadline\"");
        }
        if (splitDescription.length != 2) {
            throw new DukeException("Multiple \"/by\" not allowed in for \"deadline\"");
        }
        return new Deadline(splitDescription[0].trim(), splitDescription[1].trim());
    }

    /**
     * Creates Task from a command with event.
     *
     * @param command User input with the word event.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no duration specified by "/at".
     * @throws DukeException if there are multiple "/at".
     */
    private static Task createEvent(String command) throws DukeException {
        int charactersInEvent = 5;
        String commandDescription = command.substring(charactersInEvent);
        String[] splitDescription = commandDescription.split("/at");
        if (splitDescription[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"event\"");
        }
        // If there is only the description of the task and no event time,
        // or if the event time is whitespace, throw DukeException.
        // Must check length before checking 2nd argument or else nullPointerException.
        if (splitDescription.length == 1 || splitDescription[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/at\" specifying duration of task for \"event\"");
        }
        if (splitDescription.length != 2) {
            throw new DukeException("Multiple \"/at\" not allowed in for \"event\"");
        }
        return new Event(splitDescription[0].trim(), splitDescription[1].trim());
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
