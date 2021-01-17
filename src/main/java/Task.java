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
        if (this.type == TaskType.TODO) {
            output.append("[T]");
        } else if (this.type == TaskType.DEADLINE) {
            output.append("[D]");
        } else {
            output.append("[E]");
        }
        output.append(this.isComplete() ? "[X] " : "[ ] ");
        output.append(this.description);
        return output.toString();
    }

    public static Task parseTask(String userInput) {
        // TODO: 17/1/21 Modify function to throw error if input does not match any of the three task types
        if (userInput.startsWith("todo")) {
            return new Todo(userInput.substring(5));
        } else if (userInput.startsWith("event")) {
            return Event.parseEvent(userInput.substring(6));
        } else {
            return Deadline.parseDeadline(userInput.substring(9));
        }
    }
}
