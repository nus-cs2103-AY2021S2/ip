package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exceptions.DukeException;

/**
 * Base Task class which provides shared functionality such as description, task type and status
 */
public class Task {
    private final String description;
    private final TaskType type;
    private TaskStatus status;

    /**
     * Constructor for Task class
     *
     * @param description of task
     * @param type        of task
     * @see TaskStatus
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.status = TaskStatus.PENDING;
    }

    /**
     * Constructor for Task class with completion status specified
     *
     * @param description of task
     * @param type        of task
     * @param completion  <code>true</code> if the event is completed, <code>false</code> otherwise
     * @see TaskStatus
     */
    public Task(String description, TaskType type, Boolean completion) {
        this.description = description;
        this.type = type;
        this.status = completion ? TaskStatus.COMPLETED : TaskStatus.PENDING;
    }

    /**
     * Parse commands and creates Todo, Event or Deadline objects based on the type of command
     *
     * @param command to be parsed
     * @return Respective Task object based on command issued
     * @throws DukeException if incomplete or unrecognizable command issued
     */
    public static Task parseTask(String command) throws DukeException {
        try {
            String[] inputSplit = command.split(" ", 2);
            String entryType = inputSplit[0];
            String description = inputSplit[1].strip();
            switch (entryType) {
            case "todo":
                return Todo.parseTodo(description);
            case "event":
                return Event.parseEvent(description);
            case "deadline":
                return Deadline.parseDeadline(description);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I the description of the task cannot be empty :-(");
        }
    }

    /**
     * Parse record read from disk by Storage objects and returns the corresponding Task object
     *
     * @param fullRecord Single entry read from file
     * @return Corresponding Task object
     */
    public static Task parseRecord(String fullRecord) {
        String[] recordSplit = fullRecord.split("\\|");
        String entryType = recordSplit[0];
        Boolean completed = recordSplit[1].equals("1");
        String description = recordSplit[2];
        Task output = null;
        switch (entryType) {
        case "T":
            output = new Todo(description, completed);
            break;
        case "D":
            String[] dateTimeSplit = recordSplit[3].split(" ");
            if (dateTimeSplit.length == 2) {
                output = new Deadline(description, LocalDate.parse(dateTimeSplit[0]),
                        LocalTime.parse(dateTimeSplit[1]), completed);
            } else {
                output = new Deadline(description, LocalDate.parse(dateTimeSplit[0]),
                        completed);
            }
            break;
        case "E":
            output = new Event(description, recordSplit[3], completed);
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Returns true if task is marked as completed
     *
     * @return true if task is marked as completed
     */
    public boolean contains(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns true if task is marked as completed
     *
     * @return true if task is marked as completed
     */
    public boolean isComplete() {
        return this.status == TaskStatus.COMPLETED;
    }

    /**
     * Mark class as completed
     */
    public void markComplete() {
        this.status = TaskStatus.COMPLETED;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        switch (this.type) {
        case TODO:
            output.append("[T]");
            break;
        case EVENT:
            output.append("[E]");
            break;
        case DEADLINE:
            output.append("[D]");
            break;
        default:
            break;
        }
        output.append(this.isComplete() ? "[X] " : "[ ] ");
        output.append(this.description);
        return output.toString();
    }

    /**
     * Returns the String formatted entry for writing to disk
     *
     * @return String formatted entry to be written to disk by Storage objects
     */
    public String storageEntry() {
        StringBuilder output = new StringBuilder();
        switch (this.type) {
        case TODO:
            output.append("T");
            break;
        case EVENT:
            output.append("E");
            break;
        case DEADLINE:
            output.append("D");
            break;
        default:
            break;
        }
        output.append("|");
        output.append(this.isComplete() ? "1" : "0");
        output.append("|");
        output.append(this.description);
        return output.toString();
    }
}
