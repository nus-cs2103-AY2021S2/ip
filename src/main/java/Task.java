public class Task {
    private final String description;
    private final TaskType type;
    private TaskStatus status = TaskStatus.PENDING;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
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
}
