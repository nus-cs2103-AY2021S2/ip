package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private final String description;
    private final TaskType type;
    private TaskStatus status;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.status = TaskStatus.PENDING;
    }

    public Task(String description, TaskType type, Boolean completion) {
        this.description = description;
        this.type = type;
        this.status = completion ? TaskStatus.COMPLETED : TaskStatus.PENDING;
    }

    public boolean isComplete() {
        return this.status == TaskStatus.COMPLETED;
    }

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
        }
        output.append(this.isComplete() ? "[X] " : "[ ] ");
        output.append(this.description);
        return output.toString();
    }

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
        }
        output.append("|");
        output.append(this.isComplete() ? "1" : "0");
        output.append("|");
        output.append(this.description);
        return output.toString();
    }

    public static Task parseTask(String userInput) throws DukeException {
        try {
            String[] inputSplit = userInput.split(" ",2);
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

    public static Task parseRecord(String record) {
        String[] recordSplit = record.split("\\|");
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
        }
        return output;
    }
}
